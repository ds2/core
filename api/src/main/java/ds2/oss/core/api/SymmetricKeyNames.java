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
package ds2.oss.core.api;

import ds2.oss.core.api.crypto.AlgorithmNamed;

/**
 * All known instance names for secret key factories.
 *
 * @author dstrauss
 * @version 0.2
 */
public enum SymmetricKeyNames implements AlgorithmNamed {
    /**
     * The PBKDF2 with SHA256.
     */
    PBKDF512("PBKDF2WithHmacSHA512", 128 * 8),
    /**
     * The PBKDF2 with SHA256.
     */
    PBKDF256("PBKDF2WithHmacSHA256", 64 * 8),
    /**
     * The PBKDF2 algorithm.
     */
    PBKDF2("PBKDF2WithHmacSHA1", 160);
    /**
     * The known key length that this algorithm may produce.
     */
    private int keyLength;
    /**
     * The hash name.
     */
    private String name;

    /**
     * INits the enum value.
     *
     * @param n  the hash name
     * @param kl the key length
     */
    SymmetricKeyNames(final String n, final int kl) {
        name = n;
        keyLength = kl;
    }
    
    /**
     * Returns the suggested key length.
     *
     * @return the suggested key length
     */
    public int getSuggestedKeyLength() {
        return keyLength;
    }

    @Override
    public String getAlgorithmName() {
        return name;
    }
}
