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

package com.dangdang.ddframe.rdb.sharding.router.binding;

import java.util.ArrayList;
import java.util.Collection;

import com.dangdang.ddframe.rdb.sharding.parser.result.router.SQLBuilder;
import com.dangdang.ddframe.rdb.sharding.router.single.SingleRoutingTableFactor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

/**
 * Binding表路由表单元.
 *
 * @author zhangliang
 */
@ToString(callSuper = true)
final class BindingRoutingTableFactor extends SingleRoutingTableFactor {

    @Getter(AccessLevel.PACKAGE)
    private final Collection<BindingRoutingTableFactor> bindingRoutingTableFactors = new ArrayList<>();

    BindingRoutingTableFactor(final String logicTable, final String actualTable) {
        super(logicTable, actualTable);
    }

    @Override
    public void buildSQL(final SQLBuilder builder) {
        super.buildSQL(builder);
        for (BindingRoutingTableFactor each : bindingRoutingTableFactors) {
            each.buildSQL(builder);
        }
    }
}
