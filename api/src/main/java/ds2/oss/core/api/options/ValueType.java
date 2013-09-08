/**
 * 
 */
package ds2.oss.core.api.options;

import ds2.oss.core.api.NumericEnumValue;

/**
 * A list of known and supported value types for options.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum ValueType implements NumericEnumValue {
    /**
     * Option value is a string.
     */
    STRING(1),
    /**
     * Option value is a url.
     */
    URL(2),
    /**
     * Option value is a boolean value.
     */
    BOOLEAN(3),
    /**
     * Option value is a list.
     */
    LIST_OF_STRINGS(10);
    /**
     * The id.
     */
    private int id;
    
    /**
     * Inits the enum value.
     * 
     * @param id1
     *            the id of the entry
     */
    private ValueType(final int id1) {
        this.id = id1;
    }
    
    @Override
    public int getNumericalValue() {
        return id;
    }
}
