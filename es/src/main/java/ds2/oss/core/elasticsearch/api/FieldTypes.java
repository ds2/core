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
    STRING,
    /**
     * A boolean value.
     */
    BOOLEAN,
    /**
     * A long number.
     */
    LONG,
    /**
     * An integer number.
     */
    INTEGER,
    /**
     * A float value.
     */
    FLOAT,
    /**
     * A double value.
     */
    DOUBLE,
    /**
     * A date. In UTC.
     */
    DATE,
    /**
     * A base64 encoded string.
     */
    BINARY,
    /**
     * No idea.
     */
    NULL;
}
