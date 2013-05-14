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
package ds2.oss.core.elasticsearch.api;

/**
 * The known data types for fields.
 * 
 * @author dstrauss
 * @version 0.2
 */
public enum FieldTypes {
    /**
     * A string.
     */
    STRING("string"),
    /**
     * A boolean value.
     */
    BOOLEAN("boolean"),
    /**
     * A long number.
     */
    LONG("long"),
    /**
     * An integer number.
     */
    INTEGER("integer"),
    /**
     * A float value.
     */
    FLOAT("float"),
    /**
     * A double value.
     */
    DOUBLE("double"),
    /**
     * A date. In UTC.
     */
    DATE("date"),
    /**
     * A base64 encoded string.
     */
    BINARY("binary"),
    /**
     * No idea.
     */
    NULL("null");
    /**
     * The type name.
     */
    private String typeName;
    
    private FieldTypes(final String s) {
        typeName = s;
    }
    
    public String getTypeName() {
        return typeName;
    }
}
