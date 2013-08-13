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
@XmlEnum(String.class)
public enum RuntimeConfiguration {
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
     * A short identifier for the configuration.
     */
    private String ident;
    
    /**
     * Inits the enum.
     * 
     * @param id
     *            a short identifier string for this runtime name
     */
    private RuntimeConfiguration(final String id) {
        ident = id;
    }
    
    /**
     * Parses the given config name to be one of the known configurations.
     * 
     * @param configName
     *            the config name. This can be the name of the enum, or the shortcode.
     * @return the configuration. In case nothing has been found with the given name,
     *         {@link #LocalDevelopment} will be returned.
     */
    public static RuntimeConfiguration parseConfig(final String configName) {
        RuntimeConfiguration rc = RuntimeConfiguration.LocalDevelopment;
        for (RuntimeConfiguration c : values()) {
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
     * Returns the short identifier for this runtime configuration.
     * 
     * @return a short identifier
     */
    public String getIdentifier() {
        return ident;
    }
}
