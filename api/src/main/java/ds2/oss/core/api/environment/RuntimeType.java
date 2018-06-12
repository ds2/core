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
package ds2.oss.core.api.environment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Typical runtime configurations.
 *
 * @author dstrauss
 * @version 0.3
 */
@XmlType(namespace = "http://www.ds2/ns/oss/core/environment")
@XmlEnum
public enum RuntimeType {
    /**
     * Alpha.
     */
    @XmlEnumValue("alpha")
    Alpha("alp"),
    /**
     * Beta.
     */
    @XmlEnumValue("beta")
    Beta("bet"),
    /**
     * Live config.
     */
    @XmlEnumValue("live")
    Live("liv"),
    /**
     * Local development config.
     */
    @XmlEnumValue("localDev")
    LocalDevelopment("ldv"),
    /**
     * Pre live switching configuration.
     */
    @XmlEnumValue("preLive")
    PreLiveSwitch("plv"),
    /**
     * Prerelease config.
     */
    @XmlEnumValue("preRelease")
    PreRelease("prl"),
    /**
     * Staging config.
     */
    @XmlEnumValue("staging")
    Staging("stg");

    /**
     * Parses the given config name to be one of the known configurations.
     *
     * @param configName the config name. This can be the name of the enum, or the shortcode.
     * @return the configuration. In case nothing has been found with the given name,
     * {@link #LocalDevelopment} will be returned.
     */
    public static RuntimeType parseConfig(final String configName) {
        RuntimeType rc = RuntimeType.LocalDevelopment;
        for (RuntimeType c : values()) {
            if (c.name().equalsIgnoreCase(configName)) {
                rc = c;
            }
            if (c.getIdentifier().equalsIgnoreCase(configName)) {
                rc = c;
            }
        }
        return rc;
    }

    /**
     * A short identifier for the configuration.
     */
    private String ident;

    /**
     * Inits the enum.
     *
     * @param id a short identifier string for this runtime name
     */
    private RuntimeType(final String id) {
        ident = id;
    }

    /**
     * Returns the short identifier for this runtime configuration.
     *
     * @return a short identifier
     */
    public String getIdentifier() {
        return ident;
    }
}
