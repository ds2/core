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
