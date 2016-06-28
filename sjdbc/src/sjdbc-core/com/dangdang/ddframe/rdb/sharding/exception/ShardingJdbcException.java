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

package com.dangdang.ddframe.rdb.sharding.exception;

/**
 * JDBC分片抛出的异常基类.
 * 
 * @author zhangliang
 */
public class ShardingJdbcException extends RuntimeException {
    
    private static final long serialVersionUID = -1343739516839252250L;
    
    public ShardingJdbcException(final String errorMessage, final Object... args) {
        super(String.format(errorMessage, args));
    }

    public ShardingJdbcException(final String message, final Exception cause) {
        super(message, cause);
    }
    
    public ShardingJdbcException(final Exception cause) {
        super(cause);
    }
}
