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

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple provider for Random instances.
 * 
 * @author dstrauss
 * @version 0.3
 */
public final class RandomProvider {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * Hide constructor.
     */
    private RandomProvider() {
        // nothing to do
    }
    
    /**
     * Creates a secure randomizer.
     * 
     * @param p
     *            the injection point
     * @return a secure randomizer. Or null if an error occurred.
     */
    @Produces
    @SecureRandomizer
    public static SecureRandom createSecureRandom(final InjectionPoint p) {
        final Set<Annotation> qualifiers = p.getQualifiers();
        SecureRandom rc = null;
        for (Annotation a : qualifiers) {
            if (a instanceof SecureRandomizer) {
                final SecureRandomizer secureRandomizer = (SecureRandomizer) a;
                try {
                    if (secureRandomizer.providerName().length() <= 0) {
                        rc = SecureRandom.getInstance(secureRandomizer.algorithm());
                    } else {
                        rc = SecureRandom.getInstance(secureRandomizer.algorithm(), secureRandomizer.providerName());
                    }
                    rc.setSeed(System.currentTimeMillis());
                } catch (final NoSuchAlgorithmException e) {
                    LOG.error("Unknown algorithm!", e);
                } catch (final NoSuchProviderException e) {
                    LOG.error("Unknown provider!", e);
                }
                
            }
        }
        if (rc == null) {
            LOG.error("No secure random annotation found!");
        }
        return rc;
    }
    
    /**
     * Creates a simple randomizer.
     * 
     * @return a simple randomizer
     * @throws NoSuchAlgorithmException
     *             if an error occurred
     */
    @Produces
    public static Random createSimpleRandom() throws NoSuchAlgorithmException {
        final Random rc = new Random(System.currentTimeMillis());
        return rc;
    }
    
}
