/**
 * 
 */
package ds2.oss.core.crypto.test;

import java.util.Random;

import javax.inject.Inject;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.crypto.SecureRandomizer;

/**
 * @author dstrauss
 * 
 */
public class SecBaseDataDto implements SecurityBaseData {
    @Inject
    @SecureRandomizer
    private Random r;
    private byte[] salt;
    
    public void onLoad() {
    }
    
    @Override
    public byte[] getSalt() {
        return salt;
    }
    
    @Override
    public int getMinIteration() {
        return 65535;
    }
    
    @Override
    public byte[] getInitVector() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
