/**
 * 
 */
package ds2.oss.core.options.impl.encryption;

import java.nio.charset.Charset;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import ds2.oss.core.api.AppServerSecurityBaseDataService;
import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.EncodedContent;
import ds2.oss.core.api.crypto.EncryptionService;
import ds2.oss.core.api.options.ForValueType;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.OptionValueEncrypter;

/**
 * The string option value encrypter.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ForValueType(ValueType.STRING)
@ApplicationScoped
@Alternative
public class StringOptionValueEncrypter implements OptionValueEncrypter<String> {
    /**
     * The utf8 charset.
     */
    private static final Charset UTF8 = Charset.forName("utf-8");
    /**
     * The encryption service.
     */
    @Inject
    private EncryptionService encSvc;
    /**
     * The sec data service.
     */
    @Inject
    private AppServerSecurityBaseDataService secSvc;
    
    @Override
    public EncodedContent encrypt(final String s) {
        return encSvc.encode(secSvc.getAppserverSecretKey(), Ciphers.AES, s.getBytes(UTF8));
    }
    
    @Override
    public String decrypt(final EncodedContent s) {
        final byte[] bytes = encSvc.decode(secSvc.getAppserverSecretKey(), Ciphers.AES, s);
        final String rc = new String(bytes, UTF8);
        return rc;
    }
    
}
