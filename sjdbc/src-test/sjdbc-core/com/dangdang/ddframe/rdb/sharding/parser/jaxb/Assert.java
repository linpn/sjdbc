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

package com.dangdang.ddframe.rdb.sharding.parser.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public final class Assert {
    
    @XmlAttribute
    private String id;
    
    @XmlAttribute
    private String sql;
    
    @XmlAttribute(name = "expected-sql")
    private String expectedSQL;
    
    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table") 
    private List<Table> tables;
    
    @XmlElementWrapper(name = "condition-contexts")
    @XmlElement(name = "condition-context") 
    private List<ConditionContext> conditionContexts;
    
    @XmlElementWrapper(name = "order-by-columns")
    @XmlElement(name = "order-by-column") 
    private List<OrderByColumn> orderByColumns;
    
    @XmlElementWrapper(name = "group-by-columns")
    @XmlElement(name = "group-by-column") 
    private List<GroupByColumn> groupByColumns;
    
    @XmlElementWrapper(name = "aggregation-columns")
    @XmlElement(name = "aggregation-column") 
    private List<AggregationColumn> aggregationColumns;
    
    @XmlElement 
    private Limit limit;
}
