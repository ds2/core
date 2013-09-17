/*
 * Copyright 2012-2013 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

import javax.crypto.SecretKeyFactory;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

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
  public SecureRandom createSecureRandom(InjectionPoint p){
    Set<Annotation> qualifiers=p.getQualifiers();
    SecureRandom rc=null;
    for(Annotation a : qualifiers){
      if(a instanceof SecureRandomizer){
        SecureRandomizer secureRandomizer= (SecureRandomizer) a;
        try {
          if(secureRandomizer.providerName().length()<=0){
            rc=SecureRandom.getInstance(secureRandomizer.algorithm());
          } else {
            rc = SecureRandom.getInstance(secureRandomizer.algorithm(), secureRandomizer.providerName());
          }
          rc.setSeed(System.currentTimeMillis());
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
  @Produces
  @SecretKeyFactoryInstance(algorithm = "PBKDF2WithHmacSHA1")
  public SecretKeyFactory createSKF(InjectionPoint p){
    return null;
  }
}
