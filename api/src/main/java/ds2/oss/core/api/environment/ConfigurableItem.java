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
