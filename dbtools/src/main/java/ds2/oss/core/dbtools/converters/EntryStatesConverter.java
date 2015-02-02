/*
 * Copyright 2012-2015 Dirk Strauss
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
/**
 * 
 */
package ds2.oss.core.dbtools.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.EntryStates;

/**
 * @author dstrauss
 *
 */
@Converter
public class EntryStatesConverter implements AttributeConverter<EntryStates, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(EntryStates attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getNumericalValue();
    }
    
    @Override
    public EntryStates convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return EntryStates.getById(dbData.intValue());
    }
    
}
