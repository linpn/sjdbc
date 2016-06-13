/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.executor;

import com.codahale.metrics.Timer.Context;
import com.dangdang.ddframe.rdb.sharding.executor.event.DMLExecutionEvent;
import com.dangdang.ddframe.rdb.sharding.executor.event.DMLExecutionEventBus;
import com.dangdang.ddframe.rdb.sharding.executor.event.EventExecutionType;
import com.dangdang.ddframe.rdb.sharding.executor.wrapper.PreparedStatementExecutorWrapper;
import com.dangdang.ddframe.rdb.sharding.metrics.MetricsContext;
import com.google.common.base.Optional;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 多线程执行预编译语句对象请求的执行器.
 *
 * @author zhangliang
 * @author caohao
 */
@RequiredArgsConstructor
public final class PreparedStatementExecutor {

    private final ExecutorEngine executorEngine;

    private final Collection<PreparedStatementExecutorWrapper> preparedStatementExecutorWrappers;

    /**
     * 执行SQL查询.
     *
     * @return 结果集列表
     * @throws SQLException SQL异常
     */
    public List<ResultSet> executeQuery() throws SQLException {
        Context context = MetricsContext.start("ShardingPreparedStatement-executeQuery");
        List<ResultSet> result;
        if (1 == preparedStatementExecutorWrappers.size()) {
            result = Collections.singletonList(preparedStatementExecutorWrappers.iterator().next().getPreparedStatement().executeQuery());
            MetricsContext.stop(context);
            return result;
        }
        result = executorEngine.execute(preparedStatementExecutorWrappers, new ExecuteUnit<PreparedStatementExecutorWrapper, ResultSet>() {

            @Override
            public ResultSet execute(final PreparedStatementExecutorWrapper input) throws Exception {
                return input.getPreparedStatement().executeQuery();
            }
        });
        MetricsContext.stop(context);
        return result;
    }

    /**
     * 执行SQL更新.
     *
     * @return 更新数量
     * @throws SQLException SQL异常
     */
    public int executeUpdate() throws SQLException {
        Context context = MetricsContext.start("ShardingPreparedStatement-executeUpdate");
        postDMLExecutionEvents();
        final boolean isExceptionThrown = ExecutorExceptionHandler.isExceptionThrown();
        final Map<String, Object> dataMap = ExecutorDataMap.getDataMap();
        if (1 == preparedStatementExecutorWrappers.size()) {
            return executeUpdateInternal(preparedStatementExecutorWrappers.iterator().next(), isExceptionThrown, dataMap, Optional.fromNullable(context));
        }
        int result = executorEngine.execute(preparedStatementExecutorWrappers, new ExecuteUnit<PreparedStatementExecutorWrapper, Integer>() {

            @Override
            public Integer execute(final PreparedStatementExecutorWrapper input) throws Exception {
                return executeUpdateInternal(input, isExceptionThrown, dataMap, Optional.<Context>absent());
            }
        }, new MergeUnit<Integer, Integer>() {

            @Override
            public Integer merge(final List<Integer> results) {
                if (null == results) {
                    return 0;
                }
                int result = 0;
                for (Integer each : results) {
                    result += each;
                }
                return result;
            }
        });
        MetricsContext.stop(context);
        return result;
    }

    private int executeUpdateInternal(final PreparedStatementExecutorWrapper preparedStatementExecutorWrapper,
                                      final boolean isExceptionThrown, final Map<String, Object> dataMap, final Optional<Context> context) throws SQLException {
        int result;
        ExecutorExceptionHandler.setExceptionThrown(isExceptionThrown);
        ExecutorDataMap.setDataMap(dataMap);
        try {
            result = preparedStatementExecutorWrapper.getPreparedStatement().executeUpdate();
        } catch (final SQLException ex) {
            postDMLExecutionEventsAfterExecution(preparedStatementExecutorWrapper, EventExecutionType.EXECUTE_FAILURE);
            ExecutorExceptionHandler.handleException(ex);
            return 0;
        } finally {
            if (context.isPresent()) {
                MetricsContext.stop(context.get());
            }
        }
        postDMLExecutionEventsAfterExecution(preparedStatementExecutorWrapper, EventExecutionType.EXECUTE_SUCCESS);
        return result;
    }

    /**
     * 执行SQL请求.
     *
     * @return true表示执行DQL, false表示执行的DML
     * @throws SQLException SQL异常
     */
    public boolean execute() throws SQLException {
        Context context = MetricsContext.start("ShardingPreparedStatement-execute");
        postDMLExecutionEvents();
        final boolean isExceptionThrown = ExecutorExceptionHandler.isExceptionThrown();
        final Map<String, Object> dataMap = ExecutorDataMap.getDataMap();
        if (1 == preparedStatementExecutorWrappers.size()) {
            PreparedStatementExecutorWrapper preparedStatementExecutorWrapper = preparedStatementExecutorWrappers.iterator().next();
            return executeInternal(preparedStatementExecutorWrapper, isExceptionThrown, dataMap, Optional.fromNullable(context));
        }
        List<Boolean> result = executorEngine.execute(preparedStatementExecutorWrappers, new ExecuteUnit<PreparedStatementExecutorWrapper, Boolean>() {

            @Override
            public Boolean execute(final PreparedStatementExecutorWrapper input) throws Exception {
                return executeInternal(input, isExceptionThrown, dataMap, Optional.<Context>absent());
            }
        });
        MetricsContext.stop(context);
        return result.get(0);
    }

    private boolean executeInternal(final PreparedStatementExecutorWrapper preparedStatementExecutorWrapper,
                                    final boolean isExceptionThrown, final Map<String, Object> dataMap, final Optional<Context> context) throws SQLException {
        boolean result;
        ExecutorExceptionHandler.setExceptionThrown(isExceptionThrown);
        ExecutorDataMap.setDataMap(dataMap);
        try {
            result = preparedStatementExecutorWrapper.getPreparedStatement().execute();
        } catch (final SQLException ex) {
            postDMLExecutionEventsAfterExecution(preparedStatementExecutorWrapper, EventExecutionType.EXECUTE_FAILURE);
            ExecutorExceptionHandler.handleException(ex);
            return false;
        } finally {
            if (context.isPresent()) {
                MetricsContext.stop(context.get());
            }
        }
        postDMLExecutionEventsAfterExecution(preparedStatementExecutorWrapper, EventExecutionType.EXECUTE_SUCCESS);
        return result;
    }

    private void postDMLExecutionEvents() {
        for (PreparedStatementExecutorWrapper each : preparedStatementExecutorWrappers) {
            if (each.getDMLExecutionEvent().isPresent()) {
                DMLExecutionEventBus.post(each.getDMLExecutionEvent().get());
            }
        }
    }

    private void postDMLExecutionEventsAfterExecution(final PreparedStatementExecutorWrapper preparedStatementExecutorWrapper, final EventExecutionType eventExecutionType) {
        if (preparedStatementExecutorWrapper.getDMLExecutionEvent().isPresent()) {
            DMLExecutionEvent event = preparedStatementExecutorWrapper.getDMLExecutionEvent().get();
            event.setEventExecutionType(eventExecutionType);
            DMLExecutionEventBus.post(event);
        }
    }
}
