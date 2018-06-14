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

import ds2.oss.core.api.AssertHelper;
import ds2.oss.core.api.annotations.SecureRandomizer;
import ds2.oss.core.api.crypto.*;
import ds2.oss.core.api.dto.impl.EncodedContentDto;
import ds2.oss.core.api.dto.impl.IvEncodedContentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;

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
    @Inject
    private BytesProvider bytesProvider;
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
    @Inject
    private AssertHelper assrt;

    @Override
    public byte[] decode(final SecretKey secretKey, final AlgorithmNamed cipher, final EncodedContent src) {
        byte[] rc = null;
        assrt.assertNotNull(secretKey, "No secretkey given to decode!");
        assrt.assertNotNull(cipher, "No cipher given to use!");
        assrt.assertNotNull(src, "No content given to read from!");
        try {
            final Cipher c = secProv.createCipherInstance(cipher);
            Ciphers cipherEnum = Ciphers.getByAlgorithmName(cipher.getAlgorithmName());
            switch (cipherEnum) {
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

    @Override
    public EncodedContent encode(final SecretKey secretKey, final AlgorithmNamed cipherAlg, final byte[] src) {
        EncodedContent rc = null;
        assrt.assertNotNull(secretKey, "No secretkey given to decode!");
        assrt.assertNotNull(cipherAlg, "No cipher given to use!");
        assrt.assertNotNull(src, "No content given to read from!");
        try {
            Ciphers cipher = Ciphers.getByAlgorithmName(cipherAlg.getAlgorithmName());
            final Cipher c = secProv.createCipherInstance(cipher);
            LOG.debug("Cipher {}, sk={}", new Object[]{c, secretKey});
            switch (cipher) {
                case AES:
                    c.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(bytesProvider.createRandomByteArray(16)), random);
                    break;
                default:
                    c.init(Cipher.ENCRYPT_MODE, secretKey, random);
                    break;
            }
            // c.update(src);
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
}
