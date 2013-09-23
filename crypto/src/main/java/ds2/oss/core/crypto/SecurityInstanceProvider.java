/**
 * 
 */
package ds2.oss.core.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;

import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.KeyGeneratorNames;

/**
 * A contract for some provider based instances.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface SecurityInstanceProvider {
    /**
     * Creates a ciphers object.
     * 
     * @param c
     *            the cipher to create
     * 
     * @return the cipher, or null if an error occurred.
     */
    Cipher createCipherInstance(Ciphers c);
    
    /**
     * Creates a key generator.
     * 
     * @param name
     *            the key generator name
     * @return the instance, or null if an error occurred
     */
    KeyGenerator createKeyGenerator(KeyGeneratorNames name);
    
    /**
     * Creates a secret key factory.
     * 
     * @param string
     *            the name of the algorithm
     * @return the skf, or null if an error occurred
     */
    SecretKeyFactory createSecretKeyFactoryInstance(String string);
}
