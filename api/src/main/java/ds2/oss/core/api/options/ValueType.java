/*
 * Copyright 2012-2013 Dirk Strauss
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
package ds2.oss.core.api.options;

import ds2.oss.core.api.NumericEnumValue;

/**
 * A list of known and supported value types for options.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum ValueType implements NumericEnumValue {
    /**
     * Option value is a string.
     */
    STRING(1),
    /**
     * Option value is a url.
     */
    URL(2),
    /**
     * Option value is a boolean value.
     */
    BOOLEAN(3),
    /**
     * Option value is a list.
     */
    LIST_OF_STRINGS(10);
    /**
     * The id.
     */
    private int id;
    
    /**
     * Inits the enum value.
     * 
     * @param id1
     *            the id of the entry
     */
    private ValueType(final int id1) {
        this.id = id1;
    }
    
    @Override
    public int getNumericalValue() {
        return id;
    }
}
