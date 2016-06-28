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

package com.dangdang.ddframe.rdb.sharding.router.strategy;

import java.util.Collection;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;

/**
 * 单片键分片法接口.
 * 
 * @author zhangliang
 * 
 * @param <T> 片键类型
 */
public interface SingleKeyShardingAlgorithm<T extends Comparable<?>> extends ShardingAlgorithm {
    
    /**
     * 根据分片值和SQL的=运算符计算分片结果名称集合.
     * 
     * @param availableTargetNames 所有的可用目标名称集合, 一般是数据源或表名称
     * @param shardingValue 分片值
     * @return 分片后指向的目标名称, 一般是数据源或表名称
     */
    String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<T> shardingValue);
    
    /**
     * 根据分片值和SQL的IN运算符计算分片结果名称集合.
     * 
     * @param availableTargetNames 所有的可用目标名称集合, 一般是数据源或表名称
     * @param shardingValue 分片值
     * @return 分片后指向的目标名称集合, 一般是数据源或表名称
     */
    Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<T> shardingValue);
    
    /**
     * 根据分片值和SQL的BETWEEN运算符计算分片结果名称集合.
     * 
     * @param availableTargetNames 所有的可用目标名称集合, 一般是数据源或表名称
     * @param shardingValue 分片值
     * @return 分片后指向的目标名称集合, 一般是数据源或表名称
     */
    Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<T> shardingValue);
}
