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

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.annotations.SecureRandomizer;
import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.KeyGeneratorNames;
import ds2.oss.core.api.crypto.KeyGeneratorService;

/**
 * The AES key generator service.
 *
 * @version 0.3
 * @author dstrauss
 */
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(KeyGeneratorServiceImpl.class);
    /**
     * The security base data.
     */
    @Inject
    private SecurityBaseData baseData;
    /**
     * A randomizer.
     */
    @Inject
    @SecureRandomizer
    private SecureRandom random;
    /**
     * The security instance provider.
     */
    @Inject
    private SecurityInstanceProvider secProv;

    @Override
    public SecretKey generate(final int length, final KeyGeneratorNames name) {
        SecretKey rc;
        final KeyGenerator kgInstance = secProv.createKeyGenerator(name);
        kgInstance.init(length, random);
        rc = kgInstance.generateKey();
        return rc;
    }

    @Override
    public SecretKey generate(final String pw, final KeyGeneratorNames name) {
        SecretKey rc = null;
        try {
            final byte[] pwBytes = pw.getBytes("utf-8");
            rc = new SecretKeySpec(pwBytes, name.name());
        } catch (final UnsupportedEncodingException e) {
            LOG.error("Unknown encoding!", e);
        }
        return rc;
    }

    @Override
    public SecretKey generateSecureAesKey(final String pw) {
        return generateSecureAesKey(pw, Ciphers.AES.getSuggestedKeyLength());
    }

    @Override
    public SecretKey generateAesKey() {
        return this.generate(256, KeyGeneratorNames.AES);
    }

    @Override
    public SecretKey generateAesFromBytes(final byte[] encodedBytes) {
        return new SecretKeySpec(encodedBytes, "AES");
    }

    @Override
    public SecretKey generateSecureAesKey(String pw, int keyLength) {
        if (pw == null) {
            throw new IllegalArgumentException("No password given to use!");
        }
        SecretKey rc = null;
        try {
            final SecretKeyFactory factory = secProv.createSecretKeyFactoryInstance("PBKDF2WithHmacSHA1");
            final KeySpec keySpec
                    = new PBEKeySpec(pw.toCharArray(), baseData.getSalt(), baseData.getMinIteration(),
                            keyLength);
            final SecretKey sk1 = factory.generateSecret(keySpec);
            rc = new SecretKeySpec(sk1.getEncoded(), "AES");
        } catch (final InvalidKeySpecException e) {
            LOG.error("Invalid key spec!", e);
        }
        return rc;
    }
}
