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

package com.dangdang.ddframe.rdb.transaction.soft.storage;

import com.dangdang.ddframe.rdb.transaction.soft.datasource.TransactionLogDataSource;
import com.dangdang.ddframe.rdb.transaction.soft.datasource.impl.MemoryTransactionLogDataSource;
import com.dangdang.ddframe.rdb.transaction.soft.datasource.impl.RdbTransactionLogDataSource;
import org.junit.Test;

import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public final class TransactionLogStorageFactoryTest {
    
    @Test
    public void assertCreateMemoryTransactionLogStorageFactory() {
        TransactionLogDataSource transactionLogDataSource = new MemoryTransactionLogDataSource();
        assertThat(TransactionLogStorageFactory.createTransactionLogStorage(transactionLogDataSource), instanceOf(TransactionLogStorage.class));
    }
    
    @Test
    public void assertCreateRdbTransactionLogStorageFactory() {
        DataSource dataSource = mock(DataSource.class);
        TransactionLogDataSource transactionLogDataSource = new RdbTransactionLogDataSource(dataSource);
        assertThat(TransactionLogStorageFactory.createTransactionLogStorage(transactionLogDataSource), instanceOf(TransactionLogStorage.class));
    }
}
