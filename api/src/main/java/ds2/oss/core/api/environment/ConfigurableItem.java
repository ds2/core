package ds2.oss.core.api.environment;

/**
 * Contract for a configurable item.
 */
public interface ConfigurableItem {
    /**
     * Returns the name of the parameter name, or null if not available.
     * @return
     */
    String getParameterName();

    String getPropertyName();

    String getEnvironmentName();
}
