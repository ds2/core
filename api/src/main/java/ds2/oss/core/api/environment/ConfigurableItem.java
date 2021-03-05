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
package ds2.oss.core.api.environment;

/**
 * Contract for a configurable item.
 */
public interface ConfigurableItem {
    /**
     * Returns the name of the parameter name, or null if not available.
     *
     * @return
     */
    String getParameterName();

    /**
     * Returns the property name for this item.
     *
     * @return the property name
     */
    String getPropertyName();

    /**
     * Returns the name of the environment variable which may hold the value for this item.
     *
     * @return the env name
     */
    String getEnvironmentName();

    /**
     * Whether this value is encrypted and has to be decrypted on client side.
     *
     * @return TRUE or FALSE
     */
    boolean isEncrypted();
}
