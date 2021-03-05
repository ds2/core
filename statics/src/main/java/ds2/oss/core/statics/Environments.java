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
package ds2.oss.core.statics;

import ds2.oss.core.api.environment.ConfigurableItem;

import java.util.Map;

/**
 * Any static methods to deal with the execution environment.
 */
public interface Environments {
    /**
     * Returns the configuratble value via the local environment. This method ignores any encrypted value.
     *
     * @param itemDef            the item to find
     * @param internalProperties some internal properties if your app offers them. Set to null if unsure.
     * @return the found item value, or null if not found
     */
    static String getSystemValue(ConfigurableItem itemDef, Map<String, String> internalProperties) {
        String rc = null;
        //via internal property
        if (Methods.isBlank(rc) && !Methods.isBlank(itemDef.getPropertyName()) && internalProperties != null) {
            rc = internalProperties.get(itemDef.getPropertyName());
        }
        //via system property
        if (Methods.isBlank(rc) && !Methods.isBlank(itemDef.getPropertyName())) {
            rc = System.getProperty(itemDef.getPropertyName());
        }
        //via env
        if (Methods.isBlank(rc) && !Methods.isBlank(itemDef.getEnvironmentName())) {
            rc = System.getenv(itemDef.getEnvironmentName());
        }
        return rc;
    }
}
