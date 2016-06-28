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

package com.dangdang.ddframe.rdb.integrate.fixture;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.MultipleKeysDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public final class MultipleKeysModuloDatabaseShardingAlgorithm implements MultipleKeysDatabaseShardingAlgorithm {
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames, final Collection<ShardingValue<?>> shardingValues) {
        ShardingValue<?> shardingValue = shardingValues.iterator().next();
        switch (shardingValue.getType()) {
            case SINGLE: 
                return doEqualSharding(availableTargetNames, (ShardingValue<Integer>) shardingValue);
            case LIST: 
                return doInSharding(availableTargetNames, (ShardingValue<Integer>) shardingValue);
            case RANGE: 
                return doBetweenSharding(availableTargetNames, (ShardingValue<Integer>) shardingValue);
            default: 
                throw new UnsupportedOperationException();
        }
    }
    
    private Collection<String> doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Integer modulo = Integer.parseInt(shardingValue.getValue().toString()) % 10;
        for (String each : availableTargetNames) {
            if (each.endsWith(modulo.toString())) {
                return Collections.singletonList(each);
            }
        }
        throw new UnsupportedOperationException();
    }
    
    private Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<Integer> values = shardingValue.getValues();
        for (Integer value : values) {
            for (String dataSourceName : availableTargetNames) {
                if (dataSourceName.endsWith(value % 10 + "")) {
                    result.add(dataSourceName);
                }
            }
        }
        return result;
    }
    
    private Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % 10 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
