/**
 * 
 */
package ds2.oss.core.api.options;

/**
 * A list of known and supported value types for options.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum ValueType {
    /**
     * Option value is a string.
     */
    STRING,
    /**
     * Option value is a url.
     */
    URL,
    /**
     * Option value is a boolean value.
     */
    BOOLEAN,
    /**
     * Option value is a list.
     */
    LIST_OF_STRINGS;
}
