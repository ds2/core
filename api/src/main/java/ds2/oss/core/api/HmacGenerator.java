/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.api;

import javax.crypto.SecretKey;

/**
 * The hmac generator.
 * 
 * @author dstrauss
 * @version 0.4
 */
public interface HmacGenerator {
    /**
     * Generates a hash value for the given bytes, using the given hash algorithm. The result will
     * be a pseudo random sequence which is not equal anytime it may be invoked!
     * 
     * @param b
     *            the bytes to hash
     * @param g
     *            the hash algorithm to use
     * @return the hash value
     */
    byte[] generate(byte[] b, HashAlgorithm g);
    
    /**
     * Same as {@link #generate(byte[], HashAlgorithm)} but this time, the given key is used to
     * generate the hash.
     * 
     * @param key
     *            the key to use
     * @param b
     *            the bytes to hash
     * @param g
     *            the algorithm to use
     * @return the hash result
     */
    byte[] generate(byte[] key, byte[] b, HashAlgorithm g);
    
    /**
     * Generates a secret key using the givne bitsize and hash algorithm.
     * 
     * @param bitsize
     *            the bitsize
     * @param g
     *            the hash algorithm
     * @return the secret key
     */
    SecretKey generateKey(int bitsize, HashAlgorithm g);
}
