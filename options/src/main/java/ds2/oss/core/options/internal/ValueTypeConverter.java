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
/**
 * 
 */
package ds2.oss.core.options.internal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.options.ValueType;

/**
 * A value type converter.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Converter
public class ValueTypeConverter implements AttributeConverter<ValueType, Integer> {
    
    /*
     * (non-Javadoc)
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
     */
    @Override
    public Integer convertToDatabaseColumn(final ValueType attribute) {
        return Integer.valueOf(attribute.getNumericalValue());
    }
    
    /*
     * (non-Javadoc)
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
     */
    @Override
    public ValueType convertToEntityAttribute(final Integer dbData) {
        return ValueType.getById(dbData.intValue());
    }
    
}
