/*
 * Project group: ds2.core, core-api
 * Project:       DS/2 Core
 *
 * Copyright (C) 2012 DS/2 Software Development (http://www.ds-2.de/)
 * All rights reserved.
 *
 * See the LICENSE for more information.
 */
/**
 * 
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
     * Generates a hash value for the given bytes, using the given hash
     * algorithm. The result will be a pseudo random sequence which is not equal
     * anytime it may be invoked!
     * 
     * @param b
     *            the bytes to hash
     * @param g
     *            the hash algorithm to use
     * @return the hash value
     */
    byte[] generate(byte[] b, HashAlgorithm g);
    
    /**
     * Same as {@link #generate(byte[], HashAlgorithm)} but this time, the given
     * key is used to generate the hash.
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
