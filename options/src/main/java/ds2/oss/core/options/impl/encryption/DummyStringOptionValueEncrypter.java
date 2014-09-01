/**
 * 
 */
package ds2.oss.core.options.impl.encryption;

import java.nio.charset.Charset;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.Base64Codec;
import ds2.oss.core.api.options.ForValueType;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.OptionValueEncrypter;

/**
 * A dummy codec (not production ready) to enable testing the encryption.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
@ForValueType(ValueType.STRING)
public class DummyStringOptionValueEncrypter implements OptionValueEncrypter<String> {
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
    public String encrypt(final String s) {
        return codec.encode(s.getBytes(UTF8CS));
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.options.api.OptionValueEncrypter#decrypt(java.lang.String)
     */
    @Override
    public String decrypt(final String s) {
        final String rc = new String(codec.decode(s.toCharArray()), UTF8CS);
        return rc;
    }
    
}
