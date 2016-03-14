/*
 * Copyright 2012-2015 Dirk Strauss
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

import java.lang.invoke.MethodHandles;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.enterprise.context.ApplicationScoped;

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
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.crypto.SecurityInstanceProvider#createCipherInstance(ds2.oss.core.api.crypto
     * .Ciphers)
     */
    @Override
    public Cipher createCipherInstance(final AlgorithmNamed c) {
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
        try {
            return KeyGenerator.getInstance(name.getAlgorithmName());
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Error when creating an instance of a key generator!", e);
        }
        return null;
    }

    @Override
    public KeyPairGenerator createKeyPairGenerator(AlgorithmNamed alg) {
        try {
            return KeyPairGenerator.getInstance(alg.getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Error when creating an instance of a key generator with alg "+alg+"!", e);
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
    
    @PostConstruct
    public void onLoad() {
        LOG.debug("Using JCE default provider.");
    }
    
}
