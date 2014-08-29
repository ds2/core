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
package ds2.oss.core.options.api;

import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.impl.dto.OptionDto;
import ds2.oss.core.options.impl.dto.OptionEntity;

/**
 * A value parser.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface ValueTypeParser {
    /**
     * Parses a given object into the target type.
     * 
     * @param t
     *            the target type
     * @param targetClass
     *            the target class
     * @param thisVal
     *            the object to parse
     * @param onNull
     *            TODO
     * @param <V>
     *            the target value
     * @return the value
     */
    <V> V parseValue(ValueType t, Class<V> targetClass, Object thisVal, V onNull);
    
    /**
     * Parses a given object into a string.
     * 
     * @param valueType
     *            the value type
     * @param defaultValue
     *            the default value object.
     * @return null, or the value to persist.
     */
    String toString(ValueType valueType, Object defaultValue);
    
    <V> OptionDto<Long, V> toDto(OptionEntity e, OptionIdentifier<V> ident);
}
