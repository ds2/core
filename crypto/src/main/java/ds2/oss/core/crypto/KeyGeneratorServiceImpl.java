/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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

import ds2.oss.core.api.annotations.SecureRandomizer;
import ds2.oss.core.api.crypto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * The AES key generator service.
 *
 * @author dstrauss
 * @version 0.3
 */
@Dependent
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(KeyGeneratorServiceImpl.class);
    /**
     * The security base data.
     */
    /**
     * A randomizer.
     */
    @Inject
    @SecureRandomizer
    private SecureRandom random;
    /**
     * The AES runtime data.
     */
    @Inject
    private JavaRuntimeData runtimeData;
    /**
     * The security instance provider.
     */
    @Inject
    private SecurityInstanceProvider secProv;
    @Inject
    private BytesProvider bytesProvider;

    @Override
    public SecretKey generate(final int length, final AlgorithmNamed name) {
        SecretKey rc;
        final KeyGenerator kgInstance = secProv.createKeyGenerator(name);
        kgInstance.init(length, random);
        rc = kgInstance.generateKey();
        return rc;
    }

    @Override
    public SecretKey generate(final String pw, final AlgorithmNamed name) {
        SecretKey rc = null;
        try {
            final byte[] pwBytes = pw.getBytes("utf-8");
            rc = new SecretKeySpec(pwBytes, name.getAlgorithmName());
        } catch (final UnsupportedEncodingException e) {
            LOG.error("Unknown encoding!", e);
        }
        return rc;
    }

    @Override
    public SecretKey generateAesFromBytes(final byte[] encodedBytes) {
        return new SecretKeySpec(encodedBytes, "AES");
    }

    @Override
    public SecretKey generateAesKey() {
        int keySize = runtimeData.getAesMaxKeysize();
        return this.generate(keySize, KeyGeneratorNames.AES);
    }

    @Override
    public SecretKey generateSecureAesKey(final String pw) {
        return generateSecureAesKey(pw, runtimeData.getAesMaxKeysize());
    }

    @Override
    public SecretKey generateSecureAesKey(final String pw, final int keyLength) {
        if (pw == null) {
            throw new IllegalArgumentException("No password given to use!");
        }
        SecretKey rc = null;
        try {
            final SecretKeyFactory factory = secProv.createSecretKeyFactoryInstance("PBKDF2WithHmacSHA1");
            final KeySpec keySpec =
                    new PBEKeySpec(pw.toCharArray(), bytesProvider.createRandomByteArray(16), 65534, keyLength);
            final SecretKey sk1 = factory.generateSecret(keySpec);
            rc = new SecretKeySpec(sk1.getEncoded(), "AES");
        } catch (final InvalidKeySpecException e) {
            LOG.error("Invalid key spec!", e);
        }
        return rc;
    }
}
