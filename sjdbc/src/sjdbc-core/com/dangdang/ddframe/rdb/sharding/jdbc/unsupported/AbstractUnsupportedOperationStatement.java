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

package com.dangdang.ddframe.rdb.sharding.jdbc.unsupported;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;

import com.dangdang.ddframe.rdb.sharding.jdbc.adapter.WrapperAdapter;

/**
 * 声明不支持操作的静态语句对象.
 * 
 * @author gaohongtao
 */
public abstract class AbstractUnsupportedOperationStatement extends WrapperAdapter implements Statement {
    
    @Override
    public final int getFetchDirection() throws SQLException {
        throw new SQLFeatureNotSupportedException("getFetchDirection");
    }
    
    @Override
    public final void setFetchDirection(final int direction) throws SQLException {
        throw new SQLFeatureNotSupportedException("setFetchDirection");
    }
    
    @Override
    public final void addBatch(final String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("addBatch sql");
    }
    
    @Override
    public void clearBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("clearBatch");
    }
    
    @Override
    public int[] executeBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("executeBatch");
    }
    
    @Override
    public final void closeOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("closeOnCompletion");
    }
    
    @Override
    public final boolean isCloseOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("isCloseOnCompletion");
    }
}
