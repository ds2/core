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
package ds2.oss.core.base.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.SymmetricKeyNames;
import ds2.oss.core.api.SymmetricKeyService;

/**
 * The implementation for the symmetric key service.
 *
 * @version 0.2
 * @author dstrauss
 */
@ApplicationScoped
public class SymmetricKeyServiceImpl implements SymmetricKeyService {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SymmetricKeyServiceImpl.class);
    /**
     * The security base data.
     */
    @Inject
    private SecurityBaseData baseData;

    @Override
    public byte[] performHashing(final char[] origin, final byte[] salt, final int iterationCount,
        final SymmetricKeyNames n) {
        return performHashing(origin, salt, iterationCount, n, n.getSuggestedKeyLength());
    }

    @Override
    public byte[] performHashing(char[] origin, byte[] salt, int iterationCount, SymmetricKeyNames n, int keyLength) {
        if (origin == null) {
            LOG.warn("No origin data given to hash!");
            return null;
        }
        if (n == null) {
            throw new IllegalArgumentException("No hash algorithm given to use.");
        }
        byte[] rc = null;
        try {
            final SecretKeyFactory skf = SecretKeyFactory.getInstance(n.getName());
            final KeySpec ks = new PBEKeySpec(origin, salt, iterationCount, keyLength);
            final SecretKey erg = skf.generateSecret(ks);
            rc = erg.getEncoded();
        } catch (final NoSuchAlgorithmException e) {
            LOG.warn("Given algorithm is not known!", e);
        } catch (final InvalidKeySpecException e) {
            LOG.warn("Invalid key specification used!", e);
        }
        return rc;
    }

    @Override
    public byte[] performHashing(final char[] origin, final SymmetricKeyNames n) {
        final byte[] rc = performHashing(origin, baseData.getSalt(), baseData.getMinIteration(), n);
        return rc;
    }
}
