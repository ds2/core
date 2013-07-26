package ds2.oss.core.base.impl;

import java.security.SecureRandom;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import ds2.oss.core.api.SecurityBaseData;

/**
 * Dummy alternative for the sec base data.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Alternative
@ApplicationScoped
public class SecurityBaseDataImpl implements SecurityBaseData {
    /**
     * A randomizer.
     */
    private SecureRandom random;
    /**
     * A randomly generated salt value. On every restart of the component, a new salt value will be
     * generated!
     */
    private byte[] salt;
    
    /**
     * Inits the impl.
     */
    public SecurityBaseDataImpl() {
        random = new SecureRandom();
    }
    
    @Override
    public int getMinIteration() {
        return 1000;
    }
    
    @Override
    public byte[] getSalt() {
        return salt;
    }
    
    /**
     * Actions to perform after init, after CDI injections.
     */
    @PostConstruct
    public void onClass() {
        salt = random.generateSeed(512);
    }
}
