/**
 * 
 */
package ds2.oss.core.api;

/**
 * A helper service to convert enums to ints, and vice versa.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the enum type
 */
public interface EnumPersistenceSupport<E extends Enum<E>> {
    /**
     * Converts the given enum value into an int value.
     * 
     * @param e
     *            the enum value
     * @return the int value
     */
    int toInt(E e);
    
    /**
     * Converts a given int value into an enum value.
     * 
     * @param e
     *            the int value
     * @return the enum value, or null if not found
     */
    E toValue(int e);
}
