package ds2.oss.core.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;
import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.Nonbinding;

/**
 * Simple provider for Random instances.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class RandomProvider {
  private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Produces
    @SecureRandomizer
    public Random createRandom(InjectionPoint p)  {
      Set<Annotation> qualifiers=p.getQualifiers();
      Random rc=null;
      LOG.debug("Providers are {}", Security.getProviders());
      for(Annotation a : qualifiers){
        if(a instanceof SecureRandomizer){
          SecureRandomizer secureRandomizer= (SecureRandomizer) a;
          SecureRandom sr= null;
          try {
            if(secureRandomizer.providerName().length()<=0){
              sr=SecureRandom.getInstance(secureRandomizer.algorithm());
            } else {
              sr = SecureRandom.getInstance(secureRandomizer.algorithm(), secureRandomizer.providerName());
            }
            sr.setSeed(System.currentTimeMillis());
            rc=sr;
          } catch (NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm!",e);
          } catch (NoSuchProviderException e) {
            LOG.error("Unknown provider!",e);
          }

        }
      }
      if(rc==null){
        LOG.error("No secure random annotation found!");
      }
        return rc;
    }
    
    @Produces
    public Random createSimpleRandom() throws NoSuchAlgorithmException {
        final Random rc = new Random(System.currentTimeMillis());
        return rc;
    }
}
