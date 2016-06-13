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

package com.dangdang.ddframe.rdb.sharding.constants;

import com.dangdang.ddframe.rdb.sharding.exception.DatabaseTypeUnsupportedException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class DatabaseTypeTest {

    @Test
    public void assertValueFromSuccess() {
        assertThat(DatabaseType.valueFrom("H2"), is(DatabaseType.H2));
        assertThat(DatabaseType.valueFrom("MySQL"), is(DatabaseType.MySQL));
        assertThat(DatabaseType.valueFrom("Oracle"), is(DatabaseType.Oracle));
        assertThat(DatabaseType.valueFrom("SQLServer"), is(DatabaseType.SQLServer));
        assertThat(DatabaseType.valueFrom("DB2"), is(DatabaseType.DB2));
    }

    @Test(expected = DatabaseTypeUnsupportedException.class)
    public void assertValueFromFailure() {
        DatabaseType.valueFrom("unknown");
    }
}
