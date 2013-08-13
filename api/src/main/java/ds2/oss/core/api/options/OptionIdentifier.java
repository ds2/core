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
    Class<V> getValueType();
}
