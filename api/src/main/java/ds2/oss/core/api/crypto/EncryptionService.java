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

import javax.crypto.SecretKey;

/**
 * Encryption and decryption service.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface EncryptionService {
    /**
     * Encodes a given byte sequence.
     * 
     * @param secretKey
     *            the secret key to use
     * @param cipher
     *            the cipher to use
     * @param src
     *            the bytes to encode
     * @return the encoded object
     */
    EncodedContent encode(SecretKey secretKey, Ciphers cipher, byte[] src);
    
    /**
     * Decodes a given encoded content.
     * 
     * @param secretKey
     *            the secret key to decode
     * @param cipher
     *            the cipher
     * @param src
     *            the encoded content
     * @return the decoded bytes, or null if an error occurred
     */
    byte[] decode(SecretKey secretKey, Ciphers cipher, EncodedContent src);
}