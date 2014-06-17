/*
 * Copyright 2012-2014 Dirk Strauss
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
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.KeyGeneratorNames;

/**
 * The bouncy castle security provider.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class BouncyCastleSecurityProvider implements SecurityInstanceProvider {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.crypto.SecurityProvider#createCipherInstance(ds2.oss.core.api.crypto.Ciphers)
     */
    @Override
    public Cipher createCipherInstance(final Ciphers c) {
        try {
            return c.getCipherInstance("BC");
        } catch (final NoSuchPaddingException | NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("Error when creating the cipher instance!", e);
        }
        return null;
    }
    
    @Override
    public KeyGenerator createKeyGenerator(final KeyGeneratorNames name) {
        KeyGenerator rc = null;
        try {
            rc = KeyGenerator.getInstance(name.name(), "BC");
        } catch (final NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("Error when creating the key generator instance!", e);
        }
        return rc;
    }
    
    @Override
    public SecretKeyFactory createSecretKeyFactoryInstance(final String string) {
        SecretKeyFactory rc = null;
        try {
            rc = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1", "BC");
        } catch (final NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("Error when generating the SKF!", e);
        }
        return rc;
    }
}
