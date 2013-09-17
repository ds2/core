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
package ds2.oss.core.api.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * All supported ciphers.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum Ciphers {
    /**
     * AES cipher.
     */
    AES("AES/CBC/PKCS5Padding",256),
  /**
   * Simple DES cipher.
   */
    DES("DES/CBC/PKCS5Padding",0),
  /**
   * Triple DES cipher.
   */
  DESede("DESede/CBC/PKCS5Padding",0)
  ;
    /**
     * The instance name.
     */
    private String instanceName;
  private int suggestedKeyLength;

  /**
     * Inits the cipher enum value.
     * 
     * @param name
     *            the instance name
     */
    private Ciphers(final String name, int suggestedKeyLength) {
        instanceName = name;
      this.suggestedKeyLength=suggestedKeyLength;
    }
    
    /**
     * Returns an instance of this enum cipher value.
     * 
     * @return an instance
     * @throws NoSuchPaddingException
     *             if the padding used is unknown
     * @throws NoSuchAlgorithmException
     *             if the algorithm is unknown for this provider
     * @throws NoSuchProviderException
     *             if the provider is unknown
     */
    public Cipher getCipherInstance() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        return Cipher.getInstance(instanceName);
    }
  public Cipher getCipherInstance(String providerName) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
    return Cipher.getInstance(instanceName, providerName);
  }

  public int getSuggestedKeyLength() {
    return suggestedKeyLength;
  }
}
