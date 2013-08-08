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
package ds2.oss.core.api;

/**
 * Contract for a symmetric key hashing service.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface SymmetricKeyService {
    /**
     * Creates a hash based on the given origin data and the algorithm to use.
     * 
     * @param origin
     *            the origin data to hash
     * @param n
     *            the algorithm to use
     * @return the result hash, or null if an error occurred
     */
    byte[] performHashing(char[] origin, SymmetricKeyNames n);
    
    /**
     * An alternate method for {@link #performHashing(char[], byte[], int, SymmetricKeyNames)},
     * adding some parameters to the hashing.
     * 
     * @param origin
     *            the chars to hash
     * @param salt
     *            the salt value to use
     * @param iterationCount
     *            the iteration count
     * @param n
     *            the hash alg to use
     * @return the hashing result
     */
    byte[] performHashing(char[] origin, byte[] salt, int iterationCount, SymmetricKeyNames n);
}
