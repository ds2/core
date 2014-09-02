/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.options.api;

import ds2.oss.core.api.crypto.EncodedContent;

/**
 * Support for encrypting or decrypting option values. Use the annotation
 * {@link ds2.oss.core.api.options.ForValueType ForValueType} to address the specific
 * implementation.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the value type
 *
 */
public interface OptionValueEncrypter<V> {
    /**
     * Encrypts a given string into a base64 string.
     * 
     * @param s
     *            the string to encrypt
     * @return the encrypted value, as base64
     */
    EncodedContent encrypt(V s);
    
    /**
     * Decrypts the base64 string into the real string.
     * 
     * @param s
     *            the base64 encrypted value
     * @return the real string
     */
    V decrypt(EncodedContent s);
}
