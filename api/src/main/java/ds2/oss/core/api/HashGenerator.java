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

/**
 * Contract for a hash generator.
 * 
 * @author dstrauss
 * @version 0.4
 */
public interface HashGenerator {
    /**
     * Generates a hash value from the given bytes.
     * 
     * @param b
     *            the bytes to hash
     * @param g
     *            the algorithm to use
     * @return the hash value
     */
    byte[] getHashValue(byte[] b, HashAlgorithm g);
}
