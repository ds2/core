/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.base.impl.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.ClusterDto;

/**
 * To convert between cluster contract and db data.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Converter
public class ClusterConverter implements AttributeConverter<Cluster, String> {
    
    @Override
    public String convertToDatabaseColumn(Cluster attribute) {
        return "" + attribute.getClusterName();
    }
    
    @Override
    public Cluster convertToEntityAttribute(String dbData) {
        return new ClusterDto(dbData);
    }
    
}
