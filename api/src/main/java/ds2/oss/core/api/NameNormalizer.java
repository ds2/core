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
 * Contract for a web normalizer converter. This contract is used to convert a given string into a
 * web name component to allow the given string to be used as an identifier for REST access, or
 * similar.
 * 
 * @author dstrauss
 *         
 */
public interface NameNormalizer {
    /**
     * Normalizes the given string by removing specific characters, and/or replacing them with other
     * chars.
     * 
     * @param s
     *            the string to normalize
     * @return the result
     */
    String normalize(String s);
}
