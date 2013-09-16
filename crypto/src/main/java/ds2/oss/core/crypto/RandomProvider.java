package ds2.oss.core.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Random;

import javax.enterprise.inject.Produces;

/**
 * Simple provider for Random instances.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class RandomProvider {
    @Produces
    @SecureRandomizer
    public Random createRandom() throws NoSuchAlgorithmException, NoSuchProviderException {
        final Random rc = java.security.SecureRandom.getInstance("SHA1PRNG", "BC");
        return rc;
    }
    
    @Produces
    @SecureRandomizer
    public Random createSimpleRandom() throws NoSuchAlgorithmException {
        final Random rc = new Random(System.currentTimeMillis());
        return rc;
    }
}
