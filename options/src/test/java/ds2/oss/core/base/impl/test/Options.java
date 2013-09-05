/**
 * 
 */
package ds2.oss.core.base.impl.test;

import ds2.oss.core.options.impl.SecureStringOptionIdentifier;
import ds2.oss.core.options.impl.StringOptionIdentifier;

/**
 * The known options for this test app.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface Options {
    /**
     * The username option identifier.
     */
    StringOptionIdentifier USERNAME = new StringOptionIdentifier("testApp", "username");
    /**
     * The client secret.
     */
    SecureStringOptionIdentifier PW = new SecureStringOptionIdentifier("testApp", "clientSecret");
}
