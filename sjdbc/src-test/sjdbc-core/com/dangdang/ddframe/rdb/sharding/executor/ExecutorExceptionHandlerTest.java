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

import com.dangdang.ddframe.rdb.sharding.exception.ShardingJdbcException;
import com.dangdang.ddframe.rdb.sharding.executor.fixture.ExecutorTestUtil;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExecutorExceptionHandlerTest {
    
    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        ExecutorTestUtil.clear();
    }
    
    @Test(expected = ShardingJdbcException.class)
    public void assertHandleExceptionWithoutSet() {
        assertTrue(ExecutorExceptionHandler.isExceptionThrown());
        ExecutorExceptionHandler.handleException(new SQLException(""));
    }
    
    @Test(expected = ShardingJdbcException.class)
    public void assertHandleExceptionWhenExceptionThrownIsTrue() {
        ExecutorExceptionHandler.setExceptionThrown(true);
        assertTrue(ExecutorExceptionHandler.isExceptionThrown());
        ExecutorExceptionHandler.handleException(new SQLException(""));
    }
    
    @Test
    public void assertHandleExceptionWhenExceptionThrownIsFalse() {
        ExecutorExceptionHandler.setExceptionThrown(false);
        assertFalse(ExecutorExceptionHandler.isExceptionThrown());
        ExecutorExceptionHandler.handleException(new SQLException(""));
    }
}
