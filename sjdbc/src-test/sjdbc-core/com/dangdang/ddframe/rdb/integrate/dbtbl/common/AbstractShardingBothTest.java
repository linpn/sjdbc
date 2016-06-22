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

package com.dangdang.ddframe.rdb.integrate.dbtbl.common;

import com.dangdang.ddframe.rdb.integrate.AbstractDBUnitTest;
import com.dangdang.ddframe.rdb.sharding.jdbc.ShardingDataSource;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractShardingBothTest extends AbstractDBUnitTest {

    @Override
    protected List<String> getSchemaFiles() {
        return Arrays.asList(
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_0.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_1.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_2.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_3.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_4.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_5.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_6.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_7.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_8.sql",
                "com/dangdang/ddframe/rdb/integrate/schema/dbtbl/dbtbl_9.sql");
    }

    @Override
    protected List<String> getDataSetFiles() {
        return Arrays.asList(
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_0.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_1.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_2.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_3.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_4.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_5.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_6.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_7.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_8.xml",
                "com/dangdang/ddframe/rdb/integrate/dataset/dbtbl/init/dbtbl_9.xml");
    }

    protected abstract ShardingDataSource getShardingDataSource();
}
