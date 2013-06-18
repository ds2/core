package ds2.oss.core.base.impl;

import ds2.oss.core.api.SymmetricKeyNames;
import ds2.oss.core.api.SymmetricKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * The implementation for the symmetric key service.
 */
public class SymmetricKeyServiceImpl implements SymmetricKeyService{
    /**
     * A logger.
     */
    private static final Logger LOG= LoggerFactory.getLogger(SymmetricKeyServiceImpl.class);
    @Override
    public byte[] performHashing(byte[] origin, SymmetricKeyNames n) {
        if(origin==null){
            LOG.warn("No origin data given to hash!");
            return null;
        }
        if(n==null){
            LOG.warn("No hash algorithm given to use!");
            return null;
        }
        byte[] rc=null;
        try {
            SecretKeyFactory skf=SecretKeyFactory.getInstance(n.getName());
            KeySpec ks=new SecretKeySpec();
            SecretKey erg=skf.generateSecret(ks);
            rc=erg.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            LOG.warn("Given algorithm is not known!",e);
        } catch (InvalidKeySpecException e) {
            LOG.warn("Invalid key specification used!",e);
        }
        return rc;
    }
}
