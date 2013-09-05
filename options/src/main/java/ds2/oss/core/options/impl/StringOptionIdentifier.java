/**
 * 
 */
package ds2.oss.core.options.impl;

import ds2.oss.core.api.options.ValueType;

/**
 * A string based option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class StringOptionIdentifier extends AbstractOptionIdentifier<String> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -1717288216958232837L;
    
    /**
     * Inits the option.
     * 
     * @param appName
     *            the application name
     * @param optName
     *            the option name
     */
    public StringOptionIdentifier(final String appName, final String optName) {
        super(appName, optName, ValueType.STRING);
    }
}
