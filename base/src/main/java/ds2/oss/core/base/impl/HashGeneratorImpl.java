/*
 * Project group: ds2.core, core-crypto
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
package ds2.oss.core.base.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.HashAlgorithm;
import ds2.oss.core.api.HashGenerator;

/**
 * Basic implementation.
 * 
 * @author dstrauss
 * @version 0.4
 * 
 */
public class HashGeneratorImpl implements HashGenerator {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(HashGeneratorImpl.class);
    
    /**
     * Inits the implementation.
     */
    public HashGeneratorImpl() {
        // nothing special to do
    }
    
    @Override
    public final byte[] getHashValue(final byte[] b, final HashAlgorithm g) {
        byte[] rc = null;
        if (b == null) {
            LOG.warn("No bytes given to hash!");
        } else if (g == null) {
            LOG.warn("No algorithm given!");
        } else {
            try {
                final MessageDigest inst =
                    MessageDigest.getInstance(g.getAlgorithm());
                inst.reset();
                inst.update(b);
                rc = inst.digest();
            } catch (final NoSuchAlgorithmException e) {
                LOG.error("No provider found for this algorithm: "
                    + e.getLocalizedMessage());
            }
        }
        return rc;
    }
    
}
