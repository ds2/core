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
package ds2.oss.core.api.es;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The mappings for types.
 *
 * @author dstrauss
 * @version 0.2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TypeMapping {
    /**
     * The compress threshold. Documents bigger than the given size are compressed. Otherwise not.
     * Some values are 10kb, 100b etc. If this field is set, compress is enabled.
     */
    String compressThreshold() default "";

    /**
     * The dynamicMapping setting.
     */
    DynamicMapping dynamicMapping() default DynamicMapping.TRUE;

    /**
     * Flag to index the _timestamp value.
     */
    boolean indexTimestamp() default false;

    /**
     * The _parent declaration.
     */
    String parentType() default "";

    /**
     * Flag to indicate if the index should be refreshed on creating a document of this type.
     */
    boolean refreshIndexOnCreate() default false;

    /**
     * Flag to indicate that any dealing with this type requires routing data.
     */
    boolean requiresRouting() default false;

    /**
     * The _source.enabled flag.
     */
    boolean storeSource() default true;

    /**
     * The time to live value. Default is empty string to store the document forever. Valid values
     * may be 1d etc.
     */
    String ttl() default "";

    /**
     * The name of the index to use. Usually, this can be set to a specific index, default is to let
     * the ES service handle it for you.
     */
    String useIndex() default "";

    /**
     * The index type name.
     */
    String value();
}
