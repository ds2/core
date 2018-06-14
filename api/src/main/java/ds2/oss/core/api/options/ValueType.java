/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.api.options;

import java.net.URL;
import java.util.List;

import ds2.oss.core.api.NumericEnumValue;

/**
 * A list of known and supported value types for options.
 *
 * @author dstrauss
 * @version 0.3
 */
public enum ValueType implements NumericEnumValue {
    /**
     * Option value is a boolean value.
     */
    BOOLEAN(3, Boolean.class),
    /**
     * Option value is a list.
     */
    LIST_OF_STRINGS(10, List.class),
    /**
     * Option value is a string.
     */
    STRING(1, String.class),
    /**
     * Option value is a url.
     */
    URL(2, URL.class);
    /**
     * id lookup.
     *
     * @param i
     *            the int id
     * @return the value type, or null
     */
    public static ValueType getById(final int i) {
        for (ValueType v : values()) {
            if (v.id == i) {
                return v;
            }
        }
        return null;
    }
    
    /**
     * The id.
     */
    private int id;

    /**
     * The matching class.
     */
    private Class<?> matchingClass;

    /**
     * Inits the enum value.
     *
     * @param id1
     *            the id of the entry
     * @param c
     *            the matching class
     */
    private ValueType(final int id1, final Class<?> c) {
        id = id1;
    }

    /**
     * Returns the matching class for this value type.
     *
     * @return the matching class
     */
    public Class<?> getMatchingClass() {
        return matchingClass;
    }

    @Override
    public int getNumericalValue() {
        return id;
    }
}
