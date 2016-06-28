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

/**
 * 执行单元.
 * 
 * @param <I> 入参类型
 * @param <O> 出参类型
 * 
 * @author gaohongtao
 */
public interface ExecuteUnit<I, O> {
    
    /**
     * 执行任务.
     * 
     * @param input 输入待处理数据
     * @return 返回处理结果
     * @throws Exception 执行期异常
     */
    O execute(I input) throws Exception;
}
