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
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.EncodedContent;
import ds2.oss.core.api.crypto.EncryptionService;
import ds2.oss.core.api.crypto.IvEncodedContent;

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
     * The security instance provider.
     */
    @Inject
    private SecurityInstanceProvider secProv;
    /**
     * The security base data.
     */
    @Inject
    private SecurityBaseData baseData;
    
    @Override
    public EncodedContent encode(final SecretKey secretKey, final Ciphers cipher, final byte[] src) {
        EncodedContent rc = null;
        try {
            final Cipher c = secProv.createCipherInstance(cipher);
            LOG.debug("Cipher {}, sk={}", new Object[] { c, secretKey });
            switch (cipher) {
                case AES:
                    c.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(baseData.getInitVector()), random);
                    break;
                default:
                    c.init(Cipher.ENCRYPT_MODE, secretKey, random);
                    break;
            }
            
            final byte[] enc = c.doFinal(src);
            switch (cipher) {
                case AES:
                    final IvEncodedContentDto iv = new IvEncodedContentDto();
                    iv.setEncoded(enc);
                    iv.setInitVector(c.getIV());
                    rc = iv;
                    break;
                default:
                    final EncodedContentDto d = new EncodedContentDto();
                    d.setEncoded(enc);
                    rc = d;
                    break;
            }
        } catch (final InvalidKeyException e) {
            LOG.error("Invalid key given to encrypt!", e);
        } catch (final BadPaddingException e) {
            LOG.error("Padding error!", e);
        } catch (final IllegalBlockSizeException e) {
            LOG.error("Given block size is invalid!", e);
        } catch (final InvalidAlgorithmParameterException e) {
            LOG.error("The parameter for this algorithm is invalid!", e);
        }
        LOG.debug("Result of encoding is {}", rc);
        return rc;
    }
    
    @Override
    public byte[] decode(final SecretKey secretKey, final Ciphers cipher, final EncodedContent src) {
        byte[] rc = null;
        try {
            final Cipher c = secProv.createCipherInstance(cipher);
            switch (cipher) {
                case AES:
                    c.init(Cipher.DECRYPT_MODE, secretKey,
                        new IvParameterSpec(((IvEncodedContent) src).getInitVector()));
                    break;
                default:
                    c.init(Cipher.DECRYPT_MODE, secretKey);
                    break;
            }
            
            rc = c.doFinal(src.getEncoded());
        } catch (final InvalidKeyException e) {
            LOG.error("Invalid key!", e);
        } catch (final BadPaddingException e) {
            LOG.error("Bad padding!", e);
        } catch (final IllegalBlockSizeException e) {
            LOG.error("Block size is invalid!", e);
        } catch (final InvalidAlgorithmParameterException e) {
            LOG.error("The parameter for this algorithm is invalid!", e);
        }
        return rc;
    }
}
