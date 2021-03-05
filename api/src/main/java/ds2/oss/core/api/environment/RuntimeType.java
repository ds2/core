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

import lombok.Getter;

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
@Getter
public enum RuntimeType {
    /**
     * Alpha.
     */
    @XmlEnumValue("alpha")
    Alpha("alp", 80),
    /**
     * Beta.
     */
    @XmlEnumValue("beta")
    Beta("bet", 85),
    /**
     * Live config.
     */
    @XmlEnumValue("live")
    Live("liv", 100),
    /**
     * Local development config.
     */
    @XmlEnumValue("localDev")
    LocalDevelopment("ldv", 1),
    /**
     * Pre live switching configuration.
     */
    @XmlEnumValue("preLive")
    PreLiveSwitch("plv", 99),
    /**
     * Prerelease config.
     */
    @XmlEnumValue("preRelease")
    PreRelease("prl", 90),
    /**
     * Staging config.
     */
    @XmlEnumValue("staging")
    Staging("stg", 50),
    /**
     * User acceptance tests.
     */
    @XmlEnumValue("uat")
    UAT("uat", 60),
    /**
     * Office/department related runtime.
     */
    @XmlEnumValue("labx")
    LabX("labx", 10),
    ;

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
    private String identifier;
    /**
     * A sort order value. The higher the value, the more risky it is.
     */
    private int sortOrder;

    /**
     * Inits the enum.
     *
     * @param id a short identifier string for this runtime name
     */
    private RuntimeType(final String id, int sortOrder) {
        identifier = id;
        this.sortOrder = sortOrder;
    }

}
