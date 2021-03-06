/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.crypto;

import java.lang.invoke.MethodHandles;
import java.security.*;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.AssertHelper;
import ds2.oss.core.api.crypto.AlgorithmNamed;
import ds2.oss.core.api.crypto.KeyPairGenAlgorithm;
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
public class DefaultSecurityProvider implements SecurityInstanceProvider {
    
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private AssertHelper assrt;
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.crypto.SecurityInstanceProvider#createCipherInstance(ds2.oss.core.api.crypto
     * .Ciphers)
     */
    @Override
    public Cipher createCipherInstance(final AlgorithmNamed c) {
        assrt.assertNotNull(c, "No algorithm object given!");
        assrt.assertNotEmpty(c.getAlgorithmName(),"No algorithm name given!");
        LOG.debug("Getting cipher for alg {}", c);
        Cipher rc = null;
        try {
            rc=Cipher.getInstance(c.getAlgorithmName());
        } catch (final NoSuchPaddingException | NoSuchAlgorithmException e) {
            LOG.error("Error when creating the cipher instance!", e);
        }
        LOG.debug("Returning cipher {} for {}", new Object[] { rc, c });
        return rc;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.crypto.SecurityInstanceProvider#createKeyGenerator(ds2.oss.core.api.crypto.
     * KeyGeneratorNames)
     */
    @Override
    public KeyGenerator createKeyGenerator(final AlgorithmNamed name) {
        assrt.assertNotNull(name, "No algorithm object given!");
        assrt.assertNotEmpty(name.getAlgorithmName(),"No algorithm name given!");
        try {
            return KeyGenerator.getInstance(name.getAlgorithmName());
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm: {}", name.getAlgorithmName(), e);
        }
        return null;
    }

    @Override
    public KeyPairGenerator createKeyPairGenerator(AlgorithmNamed alg) {
        assrt.assertNotNull(alg, "No algorithm object given!");
        assrt.assertNotEmpty(alg.getAlgorithmName(),"No algorithm name given!");
        try {
            return KeyPairGenerator.getInstance(alg.getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm: {}", alg.getAlgorithmName(), e);
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
        assrt.assertNotEmpty(string,"No algorithm name given!");
        try {
            return SecretKeyFactory.getInstance(string);
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm: {}", string, e);
        }
        return null;
    }

    @Override
    public KeyAgreement createKeyAgreement(AlgorithmNamed alg) {
        assrt.assertNotNull(alg, "No algorithm object given!");
        assrt.assertNotEmpty(alg.getAlgorithmName(),"No algorithm name given!");
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
            return keyAgreement;
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm: {}", alg.getAlgorithmName(), e);
        }
        return null;
    }

    @Override
    public SecureRandom createSecureRandom(AlgorithmNamed alg) {
        assrt.assertNotNull(alg, "No algorithm object given!");
        assrt.assertNotEmpty(alg.getAlgorithmName(),"No algorithm name given!");
        try {
            return SecureRandom.getInstance(alg.getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm: {}", alg.getAlgorithmName(), e);
        }
        return null;
    }

    @Override
    public KeyFactory createKeyFactory(AlgorithmNamed alg) {
        try {
            return KeyFactory.getInstance(alg.getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm: {}", alg.getAlgorithmName(), e);
        }
        return null;
    }

    @Override
    public MessageDigest createMessageDigest(AlgorithmNamed alg) {
        try {
            return MessageDigest.getInstance(alg.getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm: {}", alg.getAlgorithmName(), e);
        }
        return null;
    }

    @PostConstruct
    public void onLoad() {
        LOG.debug("Using JCE default provider.");
    }
    
}
