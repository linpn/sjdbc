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

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public final class OrderByColumn {
    
    @XmlAttribute
    private String owner;
    
    @XmlAttribute
    private String name;
    
    @XmlAttribute
    private Integer index;
    
    @XmlAttribute
    private String alias;
    
    @XmlAttribute(name = "order-by-type") 
    private String orderByType;
}
