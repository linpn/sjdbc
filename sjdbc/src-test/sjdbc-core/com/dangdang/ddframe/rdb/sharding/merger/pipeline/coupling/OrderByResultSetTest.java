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

package com.dangdang.ddframe.rdb.sharding.merger.pipeline.coupling;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Test;

import com.dangdang.ddframe.rdb.sharding.merger.ResultSetFactory;
import com.dangdang.ddframe.rdb.sharding.merger.fixture.MockResultSet;
import com.dangdang.ddframe.rdb.sharding.parser.result.merger.MergeContext;
import com.dangdang.ddframe.rdb.sharding.parser.result.merger.OrderByColumn;
import com.dangdang.ddframe.rdb.sharding.parser.result.merger.OrderByColumn.OrderByType;

public final class OrderByResultSetTest {
    
    @Test
    public void assertNextForAsc() throws SQLException {
        ResultSet resultSet = ResultSetFactory.getResultSet(Arrays.<ResultSet>asList(new MockResultSet<>(1, 4), new MockResultSet<>(2, 4), 
                new MockResultSet<Integer>()), createMergeContext(OrderByType.ASC));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(1));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(2));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(4));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(4));
        assertFalse(resultSet.next());
    }
    
    @Test
    public void assertNextForDesc() throws SQLException {
        ResultSet resultSet = ResultSetFactory.getResultSet(Arrays.<ResultSet>asList(new MockResultSet<>(4, 1), new MockResultSet<>(4, 2), 
                new MockResultSet<Integer>()), createMergeContext(OrderByType.DESC));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(4));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(4));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(2));
        assertTrue(resultSet.next());
        assertThat(resultSet.getInt(1), is(1));
        assertFalse(resultSet.next());
    }
    
    private MergeContext createMergeContext(final OrderByType orderType) {
        MergeContext result = new MergeContext();
        result.getOrderByColumns().add(new OrderByColumn("name", orderType));
        return result;
    }
}
