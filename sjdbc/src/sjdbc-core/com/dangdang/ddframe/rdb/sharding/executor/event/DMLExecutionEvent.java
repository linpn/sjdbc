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

package com.dangdang.ddframe.rdb.sharding.executor.event;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * DML类SQL执行时事件.
 *
 * @author zhangliang
 */
@Getter
public final class DMLExecutionEvent {

    private final String id;

    private final String dataSource;

    private final String sql;

    private final List<Object> parameters;

    @Setter
    private EventExecutionType eventExecutionType;

    public DMLExecutionEvent(final String dataSource, final String sql, final EventExecutionType eventExecutionType) {
        this(dataSource, sql, Collections.emptyList(), eventExecutionType);
    }

    public DMLExecutionEvent(final String dataSource, final String sql, final List<Object> parameters, final EventExecutionType eventExecutionType) {
        // TODO 替换UUID为更有效率的id生成器
        id = UUID.randomUUID().toString();
        this.dataSource = dataSource;
        this.sql = sql;
        this.parameters = parameters;
        this.eventExecutionType = eventExecutionType;
    }
}
