/**
 * 
 */
package ds2.oss.core.options.impl;

import java.net.URL;

import ds2.oss.core.api.options.ValueType;

/**
 * A url option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class UrlOptionIdentifier extends AbstractOptionIdentifier<URL> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5641998855769963068L;
    
    public UrlOptionIdentifier(final String appName, final String optName) {
        super(appName, optName, ValueType.URL);
    }
}
