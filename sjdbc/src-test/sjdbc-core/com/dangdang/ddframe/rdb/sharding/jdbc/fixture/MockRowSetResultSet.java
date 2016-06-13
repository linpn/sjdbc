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

package com.dangdang.ddframe.rdb.sharding.jdbc.fixture;

import com.dangdang.ddframe.rdb.sharding.jdbc.adapter.AbstractRowSetResultSetAdapter;
import com.dangdang.ddframe.rdb.sharding.merger.row.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MockRowSetResultSet extends AbstractRowSetResultSetAdapter {

    private ResultSet resultSet;

    @Override
    protected void initRows(final List<ResultSet> resultSets) throws SQLException {
        this.resultSet = resultSets.get(0);
    }

    @Override
    protected Row nextRow() throws SQLException {
        if (resultSet.next()) {
            return new Row(resultSet);
        }
        return null;
    }
}
