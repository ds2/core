/**
 * 
 */
package ds2.oss.core.options.impl;

/**
 * A secure string option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class SecureStringOptionIdentifier extends StringOptionIdentifier {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5223689707309029993L;
    
    /**
     * Inits the option identifier.
     * 
     * @param appName
     * @param optName
     */
    public SecureStringOptionIdentifier(final String appName, final String optName) {
        super(appName, optName);
    }
    
    @Override
    public boolean isEncrypted() {
        return true;
    }
}
