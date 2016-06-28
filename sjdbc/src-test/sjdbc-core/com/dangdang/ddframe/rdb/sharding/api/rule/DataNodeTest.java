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

package com.dangdang.ddframe.rdb.sharding.api.rule;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DataNodeTest {
    
    @Test
    public void assertIsValidDataNode() {
        assertTrue(DataNode.isValidDataNode("ds_0.tbl_0"));
    }
    
    @Test
    public void assertIsInvalidDataNodeWithoutDelimiter() {
        assertFalse(DataNode.isValidDataNode("ds_0_tbl_0"));
    }
    
    @Test
    public void assertIsInvalidDataNodeWithTwoDelimiters() {
        assertFalse(DataNode.isValidDataNode("ds_0.tbl_0.tbl_1"));
    }
    
    @Test
    public void assertNewDataNode() {
        assertThat(new DataNode("ds_0.tbl_0").getDataSourceName(), is("ds_0"));
        assertThat(new DataNode("ds_0.tbl_0").getTableName(), is("tbl_0"));
    }
}
