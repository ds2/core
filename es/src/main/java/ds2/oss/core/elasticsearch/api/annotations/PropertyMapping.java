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
package ds2.oss.core.elasticsearch.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ds2.oss.core.elasticsearch.api.FieldTypes;
import ds2.oss.core.elasticsearch.api.IndexTypes;

/**
 * The field mapping annotation.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface PropertyMapping {
    /**
     * The field name.
     */
    String name() default "";
    
    /**
     * The data type.
     */
    FieldTypes type() default FieldTypes.STRING;
    
    /**
     * The analyzer type.
     */
    IndexTypes index() default IndexTypes.ANALYZED;
    
    /**
     * The name of the field to store in the index.
     */
    String indexName() default "";
    
    /**
     * Flag to indicate to store the field value's JSON directly.
     */
    boolean store() default false;
    
    /**
     * What to store if this field is null.
     */
    String onNull() default NULL;
    
    /**
     * The date format. See <a href=
     * "http://www.elasticsearch.org/guide/reference/mapping/date-format/" >here</a>.
     */
    String dateFormat() default NULL;
    
    /**
     * Dummy text to set null.
     */
    String NULL = "THIS IS A SPECIAL NULL VALUE - DO NOT USE";
}
