package ds2.oss.core.api.es;

/**
 * Dynamic mapping settings.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum DynamicMapping {
    /**
     * Add any new mappable fields automatically. Default setting.
     */
    TRUE,
    /**
     * Silently ignore any new mapping fields.
     */
    FALSE,
    /**
     * Crash on not registered mapping fields.
     */
    STRICT;
}
