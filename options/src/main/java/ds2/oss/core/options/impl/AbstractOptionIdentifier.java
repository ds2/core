/**
 * 
 */
package ds2.oss.core.options.impl;

import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.ValueType;

/**
 * An abstract option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the type of the option
 */
public abstract class AbstractOptionIdentifier<V> implements OptionIdentifier<V> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5342494707991378995L;
    /**
     * The option name.
     */
    private String optionName;
    /**
     * The application name.
     */
    private String applicationName;
    /**
     * the value type.
     */
    private ValueType valueType;
    
    /**
     * Inits the option identifier.
     * 
     * @param appName
     *            the application name
     * 
     * @param optName
     *            the option name
     * @param typeClass
     *            the class type
     * 
     */
    public AbstractOptionIdentifier(final String appName, final String optName, final ValueType typeClass) {
        applicationName = appName;
        optionName = optName;
        valueType = typeClass;
    }
    
    @Override
    public String getApplicationName() {
        return applicationName;
    }
    
    @Override
    public String getOptionName() {
        return optionName;
    }
    
    @Override
    public ValueType getValueType() {
        return valueType;
    }
    
    @Override
    public boolean isEncrypted() {
        return false;
    }
}
