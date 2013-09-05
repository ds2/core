/**
 * 
 */
package ds2.oss.core.options.impl;

import java.util.List;

import ds2.oss.core.api.options.ValueType;

/**
 * A list option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 * 
 */
public class ListOptionIdentifier extends AbstractOptionIdentifier<List<String>> {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -1571531634480152249L;
    
    /**
     * Inits the option identifier.
     * 
     * @param appName
     *            application name
     * @param optName
     *            the option name
     * 
     */
    public ListOptionIdentifier(final String appName, final String optName) {
        super(appName, optName, ValueType.LIST_OF_STRINGS);
    }
}
