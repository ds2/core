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

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.crypto.KeyGeneratorNames;
import ds2.oss.core.api.crypto.KeyGeneratorService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Created by dstrauss on 21.08.13.
 */
public class KeyGeneratorServiceImpl implements KeyGeneratorService {
  /**
   * A logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(KeyGeneratorServiceImpl.class);
  @Inject
  private SecurityBaseData baseData;

  @Override
  public SecretKey generate(int length, KeyGeneratorNames name) {
    SecretKey rc = null;
    try {
      KeyGenerator kgInstance = KeyGenerator.getInstance(name.name(), new BouncyCastleProvider());
      kgInstance.init(length);
      rc = kgInstance.generateKey();
    } catch (NoSuchAlgorithmException e) {
      LOG.error("Given Algorithm is unknown!", e);
    }
    return rc;
  }

  @Override
  public SecretKey generate(String pw, KeyGeneratorNames name) {
    SecretKey rc = null;
    try {
      byte[] pwBytes = pw.getBytes("utf-8");
      rc = new SecretKeySpec(pwBytes, name.name());
    } catch (UnsupportedEncodingException e) {
      LOG.error("Unknown encoding!", e);
    }
    return rc;
  }

  @Override
  public SecretKey generateSecure(String pw) {
    if(pw==null){
      throw new IllegalArgumentException("No password given to use!");
    }
    SecretKey rc = null;
    try {
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      KeySpec keySpec = new PBEKeySpec(pw.toCharArray(), baseData.getSalt(), baseData.getMinIteration(), 256);
      SecretKey sk1 = factory.generateSecret(keySpec);
      rc = new SecretKeySpec(sk1.getEncoded(), "AES");
    } catch (InvalidKeySpecException e) {
      LOG.error("Invalid key spec!", e);
    } catch (NoSuchAlgorithmException e) {
      LOG.error("Strange algorithm!", e);
    }
    return rc;
  }
}
