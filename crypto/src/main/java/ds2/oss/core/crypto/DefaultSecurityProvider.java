/**
 * 
 */
package ds2.oss.core.crypto;

import java.lang.invoke.MethodHandles;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.KeyGeneratorNames;

/**
 * The JVM default security provider.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class DefaultSecurityProvider implements SecurityInstanceProvider {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.crypto.SecurityInstanceProvider#createCipherInstance(ds2.oss.core.api.crypto
     * .Ciphers)
     */
    @Override
    public Cipher createCipherInstance(final Ciphers c) {
        try {
            return c.getCipherInstance();
        } catch (final NoSuchPaddingException | NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("Error when creating the cipher instance!", e);
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.crypto.SecurityInstanceProvider#createKeyGenerator(ds2.oss.core.api.crypto.
     * KeyGeneratorNames)
     */
    @Override
    public KeyGenerator createKeyGenerator(final KeyGeneratorNames name) {
        try {
            return KeyGenerator.getInstance(name.name());
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Error when creating an instance of a key generator!", e);
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.crypto.SecurityInstanceProvider#createSecretKeyFactoryInstance(java.lang.String)
     */
    @Override
    public SecretKeyFactory createSecretKeyFactoryInstance(final String string) {
        try {
            return SecretKeyFactory.getInstance(string);
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Error when creating the SKF!", e);
        }
        return null;
    }
    
}
