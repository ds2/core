/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.api.es;

/**
 * The known data types for fields.
 *
 * @author dstrauss
 * @version 0.2
 */
public enum FieldTypes {
    /**
     * A base64 encoded string.
     */
    BINARY("binary"),
    /**
     * A boolean value.
     */
    BOOLEAN("boolean"),
    /**
     * A date. In UTC.
     */
    DATE("date"),
    /**
     * A double value.
     */
    DOUBLE("double"),
    /**
     * A float value.
     */
    FLOAT("float"),
    /**
     * An integer number.
     */
    INTEGER("integer"),
    /**
     * A long number.
     */
    LONG("long"),
    /**
     * No idea.
     */
    NULL("null"),
    /**
     * A string.
     */
    STRING("string");
    /**
     * The type name.
     */
    private String typeName;

    /**
     * INits the enum value.
     *
     * @param s
     *            the ES data type name
     */
    private FieldTypes(final String s) {
        typeName = s;
    }

    /**
     * Returns the ES data type name.
     *
     * @return the data type name
     */
    public String getTypeName() {
        return typeName;
    }
}
