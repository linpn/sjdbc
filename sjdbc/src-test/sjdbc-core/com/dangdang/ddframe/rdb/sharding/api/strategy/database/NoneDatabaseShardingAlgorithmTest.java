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

package com.dangdang.ddframe.rdb.sharding.api.strategy.database;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public final class NoneDatabaseShardingAlgorithmTest {
    
    private final NoneDatabaseShardingAlgorithm noneDatabaseShardingAlgorithm = new NoneDatabaseShardingAlgorithm();
    
    private final Collection<String> targets = Collections.singletonList("ds");
    
    @Test
    public void assertDoSharding() {
        assertThat(noneDatabaseShardingAlgorithm.doSharding(targets, null), is(targets));
    }
    
    @Test
    public void assertDoEqualShardingForTargetsEmpty() {
        assertNull(noneDatabaseShardingAlgorithm.doEqualSharding(Collections.<String>emptyList(), null));
    }
    
    @Test
    public void assertDoEqualSharding() {
        assertThat(noneDatabaseShardingAlgorithm.doEqualSharding(targets, null), is("ds"));
    }
    
    @Test
    public void assertDoInSharding() {
        assertThat(noneDatabaseShardingAlgorithm.doInSharding(targets, null), is(targets));
    }
    
    @Test
    public void assertDoBetweenSharding() {
        assertThat(noneDatabaseShardingAlgorithm.doBetweenSharding(targets, null), is(targets));
    }
}
