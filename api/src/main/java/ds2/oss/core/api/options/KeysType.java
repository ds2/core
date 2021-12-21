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
/**
 *
 */
package ds2.oss.core.api.options;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker for types, fields etc. to indicate which key type to use.
 *
 * @author dstrauss
 * @version 0.3
 */
@Qualifier
@ApplicationScoped
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KeysType {
    /**
     * The known key types.
     *
     * @author dstrauss
     * @version 0.3
     *
     */
    enum TYPE {
        /**
         * For LONG values.
         */
        LONG,
        /**
         * For string values.
         */
        STRING;
    }

    /**
     * the type of the keys to use.
     */
    TYPE value();
}
