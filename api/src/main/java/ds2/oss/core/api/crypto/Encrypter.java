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
package ds2.oss.core.api.crypto;

/**
 * Contract for an encrypter. It is up to the implementation how to deal with the given sequence of
 * bytes to encode. Whether to use a specific hash algorithm etc.
 *
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the type of the encoded content
 */
public interface Encrypter<E extends EncodedContent> {
    /**
     * Encodes a given sequence of bytes.
     *
     * @param b
     *            the byte sequence
     * @return the encoded content.
     */
    E encode(byte[] b);
}
