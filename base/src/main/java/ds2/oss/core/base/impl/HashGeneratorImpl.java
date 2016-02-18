/*
 * Copyright 2012-2015 Dirk Strauss
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
    private static final Logger LOG = LoggerFactory.getLogger(HashGeneratorImpl.class);

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
                final MessageDigest inst = MessageDigest.getInstance(g.getAlgorithm());
                inst.reset();
                inst.update(b);
                rc = inst.digest();
            } catch (final NoSuchAlgorithmException e) {
                LOG.error("No provider found for this algorithm: " + e.getLocalizedMessage());
            }
        }
        return rc;
    }

}
