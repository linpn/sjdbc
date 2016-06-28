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

package com.dangdang.ddframe.rdb.sharding.merger.resultset.memory.row;

import com.dangdang.ddframe.rdb.sharding.merger.pipeline.coupling.aggregation.AggregationUnit;
import com.dangdang.ddframe.rdb.sharding.merger.pipeline.coupling.aggregation.AggregationUnitFactory;
import com.dangdang.ddframe.rdb.sharding.parser.result.merger.AggregationColumn;
import com.dangdang.ddframe.rdb.sharding.parser.result.merger.GroupByColumn;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 具有分组功能的数据行对象.
 * 
 * @author gaohongtao
 * @author zhangliang
 */
public final class GroupByResultSetRow extends AbstractResultSetRow {
    
    private final ResultSet resultSet;
    
    private final List<GroupByColumn> groupByColumns;
    
    private final Map<AggregationColumn, AggregationUnit> aggregationUnitMap;
    
    public GroupByResultSetRow(final ResultSet resultSet, final List<GroupByColumn> groupByColumns, final List<AggregationColumn> aggregationColumns) throws SQLException {
        super(resultSet);
        this.resultSet = resultSet;
        this.groupByColumns = groupByColumns;
        aggregationUnitMap = Maps.toMap(aggregationColumns, new Function<AggregationColumn, AggregationUnit>() {
            
            @Override
            public AggregationUnit apply(final AggregationColumn input) {
                return AggregationUnitFactory.create(input.getAggregationType());
            }
        });
    }
    
    /**
     * 处理聚合函数结果集.
     * 
     * @throws SQLException SQL异常
     */
    public void aggregate() throws SQLException {
        for (Map.Entry<AggregationColumn, AggregationUnit> each : aggregationUnitMap.entrySet()) {
            each.getValue().merge(getAggregationValues(each.getKey().getDerivedColumns().isEmpty() ? Collections.singletonList(each.getKey()) : each.getKey().getDerivedColumns()));
        }
    }
    
    private List<Comparable<?>> getAggregationValues(final List<AggregationColumn> aggregationColumns) throws SQLException {
        List<Comparable<?>> result = new ArrayList<>(aggregationColumns.size());
        for (AggregationColumn each : aggregationColumns) {
            result.add((Comparable<?>) resultSet.getObject(each.getColumnIndex()));
        }
        return result;
    }
    
    /**
     * 生成结果.
     */
    public void generateResult() {
        for (AggregationColumn each : aggregationUnitMap.keySet()) {
            setCell(each.getColumnIndex(), aggregationUnitMap.get(each).getResult());
        }
    }
    
    /**
     * 获取分组值.
     * 
     * @return 分组值集合
     * @throws SQLException SQL异常
     */
    public List<Object> getGroupByValues() throws SQLException {
        List<Object> result = new ArrayList<>(groupByColumns.size());
        for (GroupByColumn each : groupByColumns) {
            result.add(resultSet.getObject(each.getColumnIndex()));
        }
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("GroupByKey is: ");
        result.append(Lists.transform(groupByColumns, new Function<GroupByColumn, Object>() {
            
            @Override
            public Object apply(final GroupByColumn input) {
                return getCell(input.getColumnIndex());
            }
        }));
        if (aggregationUnitMap.isEmpty()) {
            return result.toString();
        }
        result.append("; Aggregation result is: ").append(Lists.transform(new ArrayList<>(aggregationUnitMap.keySet()), new Function<AggregationColumn, String>() {
            
            @Override
            public String apply(final AggregationColumn input) {
                Object value = getCell(input.getColumnIndex());
                value = null == value ? "null" : value;
                return String.format("{index:%d, type:%s, value:%s}", input.getColumnIndex(), input.getAggregationType(), value);
            }
        }));
        return result.toString();
    }
}
