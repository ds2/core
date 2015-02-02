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

/**
 * All known instance names for secret key factories.
 *
 * @version 0.2
 * @author dstrauss
 */
public enum SymmetricKeyNames {
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
     * @param n
     *            the hash name
     * @param kl
     *            the key length
     */
    private SymmetricKeyNames(final String n, final int kl) {
        name = n;
        keyLength = kl;
    }

    /**
     * Returns the hash algorithm name.
     *
     * @return the hash algorithm name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the suggested key length.
     *
     * @return the suggested key length
     */
    public int getSuggestedKeyLength() {
        return keyLength;
    }
}
