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
/**
 * 
 */
package ds2.oss.core.base.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.HashAlgorithm;
import ds2.oss.core.api.HmacGenerator;

/**
 * The hmac version.
 * 
 * @author dstrauss
 * @version 0.4
 */
public class HmacGeneratorImpl implements HmacGenerator {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HmacGeneratorImpl.class);
    
    /**
     * Inits the implementation.
     */
    public HmacGeneratorImpl() {
        // nothing special to do
    }
    
    /**
     * Generates the hmac id.
     * 
     * @param g
     *            the hash algorithm to use for the mac hashing.
     * @return the id, or null if not found
     */
    private static String generateHmacId(final HashAlgorithm g) {
        String hmacId = null;
        switch (g) {
            case MD5:
                hmacId = "HmacMD5";
                break;
            case SHA1:
                hmacId = "HmacSHA1";
                break;
            default:
                LOG.warn("The given algorithm {} is unknown!", g);
                break;
        }
        return hmacId;
    }
    
    /**
     * Generates the mac hash.
     * 
     * @param sk
     *            the secret key to use
     * @param hmacId
     *            the hmac id
     * @param b
     *            the bytes to hash
     * @return the hash value
     */
    private static byte[] generateMac(final SecretKey sk, final String hmacId, final byte[] b) {
        Mac mc;
        try {
            mc = Mac.getInstance(hmacId);
            mc.init(sk);
            mc.update(b);
            return mc.doFinal();
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("No provider found for macId {}!", hmacId);
        } catch (final InvalidKeyException e) {
            LOG.error("Invalid key given to use!", e);
        }
        return null;
    }
    
    @Override
    public final byte[] generate(final byte[] key, final byte[] b, final HashAlgorithm g) {
        byte[] rc = null;
        if (g == null) {
            LOG.warn("No hash algorithm given to use!");
        } else if (b == null) {
            LOG.warn("No bytes given to hash.");
        } else if (key == null) {
            LOG.warn("No key given to use!");
        } else {
            final String hmacId = generateHmacId(g);
            final SecretKey sk = new SecretKeySpec(key, hmacId);
            rc = generateMac(sk, hmacId, b);
        }
        return rc;
    }
    
    @Override
    public final byte[] generate(final byte[] b, final HashAlgorithm g) {
        byte[] rc = null;
        if (g == null) {
            LOG.warn("No hash algorithm given to use!");
        } else if (b == null) {
            LOG.warn("No bytes given to hash.");
        } else {
            KeyGenerator gen = null;
            final String hmacId = generateHmacId(g);
            try {
                gen = KeyGenerator.getInstance(hmacId);
                final SecretKey sk = gen.generateKey();
                rc = generateMac(sk, hmacId, b);
            } catch (final NoSuchAlgorithmException e) {
                LOG.error("No provider found for macId {}!", hmacId);
            }
        }
        return rc;
    }
    
    @Override
    public SecretKey generateKey(final int bitsize, final HashAlgorithm g) {
        KeyGenerator gen;
        SecretKey rc = null;
        try {
            gen = KeyGenerator.getInstance(generateHmacId(g));
            gen.init(bitsize);
            rc = gen.generateKey();
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("No provider found for given hash.", e);
        }
        return rc;
    }
    
}
