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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The field mapping annotation to mark a field being a part of a json document for elasticsearch.
 *
 * @author dstrauss
 * @version 0.2
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface PropertyMapping {
    /**
     * The default boost value.
     */
    float DEF_BOOST = 1.0f;

    /**
     * Dummy text to set null.
     */
    String NULL = "THIS IS A SPECIAL NULL VALUE - DO NOT USE";

    /**
     * The boost value.
     */
    float boost() default DEF_BOOST;

    /**
     * The date format. See <a href=
     * "http://www.elasticsearch.org/guide/reference/mapping/date-format/" >here</a>.
     */
    String dateFormat() default NULL;

    /**
     * The analyzer type.
     */
    IndexTypes index() default IndexTypes.ANALYZED;

    /**
     * The name of the field to store in the index.
     */
    String indexName() default "";

    /**
     * The field name.
     */
    String name() default "";

    /**
     * What to store if this field is null.
     */
    String onNull() default NULL;

    /**
     * Flag to indicate to store the field value's JSON directly.
     */
    boolean store() default false;

    /**
     * The data type.
     */
    FieldTypes type() default FieldTypes.STRING;
}
