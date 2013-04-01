/**
 * 
 */
package ds2.oss.core.api;

/**
 * Contract for converting enum values into numerical values.
 * 
 * @author dstrauss
 * @version 0.1
 */
public interface NumericEnumValue {
    /**
     * Returns the numerical value for this enum value.
     * 
     * @return the enum value
     */
    int getNumericalValue();
}
