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
 * The known hash algorithms.
 * 
 * @author dstrauss
 * @version 0.4
 */
public enum HashAlgorithm {
    /**
     * The MD5 hash.
     */
    MD5("md5"),
    /**
     * The SHA1 hash.
     */
    SHA1("sha-1"),
    /**
     * The SHA 256 hash.
     */
    SHA256("sha-256"),
    /**
     * The sha 512 algorithm.
     */
    SHA512("sha-512");
    /**
     * The algorithm id.
     */
    private String alg;
    
    /**
     * Inits the enum value.
     * 
     * @param s
     *            the algorithm id
     */
    private HashAlgorithm(final String s) {
        alg = s;
    }
    
    /**
     * Returns the algorithm id for this hash type.
     * 
     * @return the algorithm id
     */
    public String getAlgorithm() {
        return alg;
    }
}
