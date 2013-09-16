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

import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.EncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.enterprise.context.ApplicationScoped;
import java.lang.invoke.MethodHandles;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidParameterSpecException;

/**
 * The encryption service.
 */
@ApplicationScoped
public class EncryptionServiceImpl implements EncryptionService {
  private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Override
  public byte[] encode(SecretKey secretKey, Ciphers cipher, byte[] src) {
    byte[] rc = null;
    try {
      Cipher c = cipher.getCipherInstance();
      c.init(Cipher.ENCRYPT_MODE, secretKey);
      rc = c.doFinal(src);
      byte[] iv=c.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
    } catch (NoSuchPaddingException e) {
      LOG.error("Padding unknown!",e);
    } catch (NoSuchAlgorithmException e) {
      LOG.error("Algorithm unknown!",e);
    } catch (InvalidKeyException e) {
      LOG.error("Invalid key given to encrypt!",e);
    } catch (BadPaddingException e) {
      LOG.error("Padding error!",e);
    } catch (IllegalBlockSizeException e) {
      LOG.error("Given block size is invalid!",e);
    } catch (NoSuchProviderException e) {
      LOG.error("Given provider is unknown!",e);
    } catch (InvalidParameterSpecException e) {
      LOG.error("Given encoding parameter is invalid!",e);
    }
    return rc;
  }

  @Override
  public byte[] decode(SecretKey secretKey, Ciphers cipher, byte[] src) {
    byte[] rc = null;
    try {
      Cipher c = cipher.getCipherInstance();
      c.init(Cipher.DECRYPT_MODE, secretKey);
      rc = c.doFinal(src);
    } catch (NoSuchPaddingException e) {
    } catch (NoSuchAlgorithmException e) {
    } catch (InvalidKeyException e) {
    } catch (BadPaddingException e) {
    } catch (IllegalBlockSizeException e) {
    } catch (NoSuchProviderException e) {
      e.printStackTrace();
    }
    return rc;
  }
}
