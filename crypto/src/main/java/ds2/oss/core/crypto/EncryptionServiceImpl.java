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

import java.lang.invoke.MethodHandles;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.EncryptionService;

/**
 * The encryption service.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class EncryptionServiceImpl implements EncryptionService {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * A randomizer.
     */
    @Inject
    @SecureRandomizer
    private SecureRandom random;
    /**
     * The security base data.
     */
    @Inject
    private SecurityBaseData baseData;
    
    @Override
    public byte[] encode(final SecretKey secretKey, final Ciphers cipher, final byte[] src) {
        byte[] rc = null;
        try {
            final Cipher c = cipher.getCipherInstance("BC");
            LOG.debug("Cipher {}, sk={}", new Object[] { c, secretKey });
            c.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(baseData.getInitVector()), random);
            rc = c.doFinal(src);
        } catch (final NoSuchPaddingException e) {
            LOG.error("Padding unknown!", e);
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Algorithm unknown!", e);
        } catch (final InvalidKeyException e) {
            LOG.error("Invalid key given to encrypt!", e);
        } catch (final BadPaddingException e) {
            LOG.error("Padding error!", e);
        } catch (final IllegalBlockSizeException e) {
            LOG.error("Given block size is invalid!", e);
        } catch (final NoSuchProviderException e) {
            LOG.error("Given provider is unknown!", e);
        } catch (final InvalidAlgorithmParameterException e) {
            LOG.error("The parameter for this algorithm is invalid!", e);
        }
        return rc;
    }
    
    @Override
    public byte[] decode(final SecretKey secretKey, final Ciphers cipher, final byte[] src) {
        byte[] rc = null;
        try {
            final Cipher c = cipher.getCipherInstance("BC");
            c.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(baseData.getInitVector()));
            rc = c.doFinal(src);
        } catch (final NoSuchPaddingException e) {
            LOG.error("Padding unknown!", e);
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Unknown Algorithm!", e);
        } catch (final InvalidKeyException e) {
            LOG.error("Invalid key!", e);
        } catch (final BadPaddingException e) {
            LOG.error("Bad padding!", e);
        } catch (final IllegalBlockSizeException e) {
            LOG.error("Block size is invalid!", e);
        } catch (final NoSuchProviderException e) {
            LOG.error("Given provider is unknown!", e);
        } catch (final InvalidAlgorithmParameterException e) {
            LOG.error("The parameter for this algorithm is invalid!", e);
        }
        return rc;
    }
}
