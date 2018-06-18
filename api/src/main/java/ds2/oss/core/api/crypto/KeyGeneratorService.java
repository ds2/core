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
package ds2.oss.core.api.crypto;

import javax.crypto.SecretKey;

/**
 * To generate some keys.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface KeyGeneratorService {
    
    /**
     * Creates a secret key.
     *
     * @param length
     *            the key size
     * @param name
     *            the algorithm to use
     * @return the key, or null if an error occurred
     */
    SecretKey generate(int length, AlgorithmNamed name);
    
    /**
     * Generates a very plain secret key.
     *
     * @param pw
     *            the password to use the bytes from
     * @param name
     *            the key spec name to use
     *
     * @return the key, or null
     */
    SecretKey generate(String pw, AlgorithmNamed name);
    
    /**
     * Generates an AES Secret key from a serialized version of a key.
     *
     * @param encodedBytes
     *            the encoded bytes of the key
     * @return the key, or null in case of an error
     */
    SecretKey generateAesFromBytes(byte[] encodedBytes);
    
    /**
     * Generates an AES key with 256 bit length.
     *
     * @return the AES key to use
     */
    SecretKey generateAesKey();
    
    /**
     * Generates an AES key.
     *
     * @param pw
     *            the password to use the bytes from
     *
     * @return the secret key
     */
    SecretKey generateSecureAesKey(String pw);
    
    /**
     * Generates an AES key.
     *
     * @param pw
     *            the password to use the bytes from
     * @param keyLength
     *            the key length.
     *
     * @return the secret key
     */
    SecretKey generateSecureAesKey(String pw, int keyLength);
}
