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

package com.dangdang.ddframe.rdb.sharding.spring;

import java.io.File;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.dangdang.ddframe.rdb.sharding.jdbc.ShardingDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import lombok.Getter;

@TestExecutionListeners(inheritListeners = false, listeners =
    {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public abstract class AbstractSpringDBUnitTest extends AbstractJUnit4SpringContextTests {
    
    @Resource
    @Getter
    private ShardingDataSource shardingDataSource;
    
    private final ClassLoader classLoader = AbstractSpringDBUnitTest.class.getClassLoader();
    
    @Before
    public void createSchema() throws SQLException {
        for (String each : getSchemaFiles()) {
            RunScript.execute(createDataSource(each).getConnection(), new InputStreamReader(classLoader.getResourceAsStream(each)));
        }
    }
    
    private DataSource createDataSource(final String dataSetFile) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(org.h2.Driver.class.getName());
        result.setUrl(String.format("jdbc:h2:mem:%s;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MYSQL", getFileName(dataSetFile)));
        result.setUsername("sa");
        result.setPassword("");
        result.setMaxActive(100);
        return result;
    }
    
    private String getFileName(final String dataSetFile) {
        String fileName = new File(dataSetFile).getName();
        if (-1 == fileName.lastIndexOf(".")) {
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
    
    protected List<String> getSchemaFiles() {
        return Arrays.asList("schema/dbtbl_0.sql", "schema/dbtbl_1.sql");
    }
}
