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
