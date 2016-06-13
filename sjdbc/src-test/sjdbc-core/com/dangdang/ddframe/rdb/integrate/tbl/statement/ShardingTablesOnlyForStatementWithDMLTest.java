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

package com.dangdang.ddframe.rdb.integrate.tbl.statement;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import com.dangdang.ddframe.rdb.integrate.tbl.AbstractShardingTablesOnlyDBUnitTest;
import com.dangdang.ddframe.rdb.sharding.jdbc.ShardingDataSource;
import com.dangdang.ddframe.rdb.sharding.parser.result.router.SQLStatementType;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class ShardingTablesOnlyForStatementWithDMLTest extends AbstractShardingTablesOnlyDBUnitTest {

    private ShardingDataSource shardingDataSource;

    @Before
    public void init() throws SQLException {
        shardingDataSource = getShardingDataSource();
    }

    @Test
    public void assertInsert() throws SQLException, DatabaseUnitException {
        String sql = "INSERT INTO `t_order` (`order_id`, `user_id`, `status`) VALUES (%s, %s, '%s')";
        for (int i = 1; i <= 10; i++) {
            try (Connection connection = shardingDataSource.getConnection()) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(String.format(sql, i, i, "insert"));
            }
        }
        assertDataSet("insert", "insert");
    }

    @Test
    public void assertUpdate() throws SQLException, DatabaseUnitException {
        ShardingDataSource shardingDataSource = getShardingDataSource();
        String sql = "UPDATE `t_order` SET `status` = '%s' WHERE `order_id` = %s AND `user_id` = %s";
        for (int i = 10; i < 12; i++) {
            for (int j = 0; j < 10; j++) {
                try (Connection connection = shardingDataSource.getConnection()) {
                    Statement stmt = connection.createStatement();
                    assertThat(stmt.executeUpdate(String.format(sql, "updated", i * 100 + j, i)), is(1));
                }
            }
        }
        assertDataSet("update", "updated");
    }

    @Test
    public void assertUpdateWithoutShardingValue() throws SQLException, DatabaseUnitException {
        ShardingDataSource shardingDataSource = getShardingDataSource();
        String sql = "UPDATE `t_order` SET `status` = '%s' WHERE `status` = '%s'";
        try (Connection connection = shardingDataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            assertThat(stmt.executeUpdate(String.format(sql, "updated", "init")), is(20));
        }
        assertDataSet("update", "updated");
    }


    @Test
    public void assertDelete() throws SQLException, DatabaseUnitException {
        ShardingDataSource shardingDataSource = getShardingDataSource();
        String sql = "DELETE `t_order` WHERE `order_id` = %s AND `user_id` = %s AND `status` = '%s'";
        for (int i = 10; i < 12; i++) {
            for (int j = 0; j < 10; j++) {
                try (Connection connection = shardingDataSource.getConnection()) {
                    Statement stmt = connection.createStatement();
                    assertThat(stmt.executeUpdate(String.format(sql, i * 100 + j, i, "init")), is(1));
                }
            }
        }
        assertDataSet("delete", "init");
    }

    @Test
    public void assertDeleteWithoutShardingValue() throws SQLException, DatabaseUnitException {
        ShardingDataSource shardingDataSource = getShardingDataSource();
        String sql = "DELETE `t_order` WHERE `status` = '%s'";
        try (Connection connection = shardingDataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            assertThat(stmt.executeUpdate(String.format(sql, "init")), is(20));
        }
        assertDataSet("delete", "init");
    }

    private void assertDataSet(final String expectedDataSetPattern, final String status) throws SQLException, DatabaseUnitException {
        for (int i = 0; i < 10; i++) {
            assertDataSet(String.format("integrate/dataset/tbl/expect/%s/db_single.xml", expectedDataSetPattern),
                    shardingDataSource.getConnection().getConnection("dataSource_db_single", SQLStatementType.SELECT),
                    String.format("t_order_%s", i), String.format("SELECT * FROM `t_order_%s` WHERE `status`=?", i), status);
        }
    }
}
