package ds2.oss.core.api.options;

import java.io.Serializable;

/**
 * The keys to uniquely identify a single option.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the value type
 */
public interface OptionIdentifier<V> extends Serializable {
    /**
     * Returns the application name of this option.
     * 
     * @return the application name
     */
    String getApplicationName();
    
    /**
     * Returns the option name.
     * 
     * @return the name of the option
     */
    String getOptionName();
    
    /**
     * Returns the value type for this option.
     * 
     * @return the value type
     */
    ValueType getValueType();
    
    /**
     * Flag to indicate that this option contains an encrypted value and must be decoded.
     * 
     * @return TRUE if the values for this option must be stored in an encrypted manor, otherwise
     *         and by default FALSE.
     */
    boolean isEncrypted();
}
