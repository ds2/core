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
package ds2.oss.core.crypto.bc;

import java.lang.invoke.MethodHandles;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

import ds2.oss.core.api.crypto.*;
import ds2.oss.core.statics.Securitix;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.crypto.DefaultSecurityProvider;
import ds2.oss.core.crypto.SecurityInstanceProvider;

/**
 * The bouncy castle security provider.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Specializes
public class BouncyCastleSecurityProvider extends DefaultSecurityProvider implements SecurityInstanceProvider {
    
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The BC provider name.
     */
    private static final String ID = BouncyCastleProvider.PROVIDER_NAME;
    
    /**
     * Actions to perform at startup.
     */
    @Override
	@PostConstruct
    public void onLoad() {
        LOG.debug("Loading BC Provider");
        Securitix.installProvider(new BouncyCastleProvider(), 1);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.crypto.SecurityProvider#createCipherInstance(ds2.oss.core.api.crypto.Ciphers)
     */
    @Override
    public Cipher createCipherInstance(final AlgorithmNamed c) {
        Cipher rc = null;
        try {
            rc = Cipher.getInstance(c.getAlgorithmName(), ID);
        } catch (final NoSuchPaddingException | NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("Error when creating the cipher instance!", e);
        }
        LOG.debug("Using {} -> {}", new Object[] { c, rc });
        return rc;
    }
    
    @Override
    public KeyGenerator createKeyGenerator(final AlgorithmNamed name) {
        KeyGenerator rc = null;
        try {
            rc = KeyGenerator.getInstance(name.getAlgorithmName(), ID);
        } catch (final NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("Error when creating the key generator instance!", e);
        }
        return rc;
    }
    
    @Override
    public SecretKeyFactory createSecretKeyFactoryInstance(final String string) {
        SecretKeyFactory rc = null;
        try {
            rc = SecretKeyFactory.getInstance(SecretKeyFactories.PBKDF2WithHmacSHA1.getAlgorithmName(), ID);
        } catch (final NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("Error when generating the SKF!", e);
        }
        return rc;
    }

    @Override
    public KeyPairGenerator createKeyPairGenerator(AlgorithmNamed alg) {
        try {
            return KeyPairGenerator.getInstance(alg.getAlgorithmName(), ID);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Given algorithm is unknown to this provider!", e);
        } catch (NoSuchProviderException e) {
            LOG.error("Given provider is unknown!", e);
        }
        return null;
    }
}
