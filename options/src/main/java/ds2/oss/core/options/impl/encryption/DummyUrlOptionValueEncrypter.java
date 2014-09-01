/**
 * 
 */
package ds2.oss.core.options.impl.encryption;

import java.net.URL;
import java.nio.charset.Charset;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.Base64Codec;
import ds2.oss.core.api.options.ForValueType;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.Tools;
import ds2.oss.core.options.api.OptionValueEncrypter;

/**
 * A dummy url option value encrypter. This encrypter simply uses base64 to encrypt or decrypt a
 * given url.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
@ForValueType(ValueType.URL)
public class DummyUrlOptionValueEncrypter implements OptionValueEncrypter<URL> {
    /**
     * The utf8 charset.
     */
    private static final Charset UTF8 = Charset.forName("utf-8");
    /**
     * The base64 codec.
     */
    @Inject
    private Base64Codec b64;
    
    @Override
    public String encrypt(URL s) {
        return b64.encode(s.toString().getBytes(UTF8));
    }
    
    @Override
    public URL decrypt(String s) {
        URL rc = null;
        final byte[] bytes = b64.decode(s.toCharArray());
        final String urlStr = new String(bytes, UTF8);
        rc = Tools.toUrl(urlStr);
        return rc;
    }
    
}
