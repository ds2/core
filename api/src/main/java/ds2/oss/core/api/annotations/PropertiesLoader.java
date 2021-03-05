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
package ds2.oss.core.api.annotations;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import javax.validation.constraints.Null;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * To load a properties file from a specific location.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
@Qualifier
public @interface PropertiesLoader {
    /**
     * A path to a file location.
     * @return the file path
     */
    @Nonbinding
    String filePath() default "";

    /**
     * A path from a system property.
     * @return a system property name that contains the path
     */
    @Nonbinding
    String sysProp() default "";

    /**
     * A path from an environment variable. This is tried first.
     * @return a path from an environment variable
     */
    @Nonbinding
    String envProp() default "";

    /**
     * A resource to load the properties from.
     * @return a resource name
     */
    @Nonbinding
    String resource() default "";
    @Nonbinding
    boolean setNullOnFail() default false;
}
