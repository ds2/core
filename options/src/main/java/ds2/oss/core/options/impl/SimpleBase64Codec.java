/**
 * 
 */
package ds2.oss.core.options.impl;

import java.nio.charset.Charset;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.Base64Codec;
import ds2.oss.core.options.api.OptionValueEncrypter;

/**
 * A dummy codec (not production ready) to enable testing the encryption.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
public class SimpleBase64Codec implements OptionValueEncrypter {
    /**
     * The charset to encode and decode.
     */
    private static final Charset UTF8CS = Charset.forName("utf-8");
    /**
     * The codec to use.
     */
    @Inject
    private Base64Codec codec;
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.options.api.OptionValueEncrypter#encrypt(java.lang.String)
     */
    @Override
    public String encrypt(String s) {
        return codec.encode(s.getBytes(UTF8CS));
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.options.api.OptionValueEncrypter#decrypt(java.lang.String)
     */
    @Override
    public String decrypt(String s) {
        final String rc = new String(codec.decode(s.toCharArray()), UTF8CS);
        return rc;
    }
    
}
