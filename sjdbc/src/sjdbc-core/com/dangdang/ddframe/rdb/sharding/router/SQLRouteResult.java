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

package com.dangdang.ddframe.rdb.sharding.router;

import java.util.HashSet;
import java.util.Set;

import com.dangdang.ddframe.rdb.sharding.parser.result.merger.MergeContext;
import com.dangdang.ddframe.rdb.sharding.parser.result.router.SQLStatementType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * SQL路由结果.
 *
 * @author gaohongtao
 */
@RequiredArgsConstructor
@Getter
public final class SQLRouteResult {

    private final SQLStatementType sqlStatementType;

    private final MergeContext mergeContext;

    private final Set<SQLExecutionUnit> executionUnits = new HashSet<>();
}
