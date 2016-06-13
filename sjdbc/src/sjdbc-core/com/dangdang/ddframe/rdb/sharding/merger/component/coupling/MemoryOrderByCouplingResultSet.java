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

package com.dangdang.ddframe.rdb.sharding.merger.component.coupling;

import com.dangdang.ddframe.rdb.sharding.merger.component.ComponentResultSet;
import com.dangdang.ddframe.rdb.sharding.merger.component.other.MemoryOrderByResultSet;
import com.dangdang.ddframe.rdb.sharding.parser.result.merger.OrderByColumn;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

/**
 * 基于内存的全排序.
 *
 * @author gaohongtao
 */
public class MemoryOrderByCouplingResultSet extends MemoryOrderByResultSet implements CouplingResultSet {

    public MemoryOrderByCouplingResultSet(final List<OrderByColumn> expectOrderList) {
        super(expectOrderList);
    }

    @Override
    public ComponentResultSet init(final ResultSet preResultSet) {
        setResultSets(Collections.singletonList(preResultSet));
        return this;
    }
}
