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
 * A service to support binary operations.
 *
 * @author dstrauss
 * @version 0.1
 */
public interface BitSupport {
    /**
     * Creates an int based on the given sequence of bytes.
     *
     * @param b
     *            the bytes
     * @return an int value
     */
    int createInt(final byte... b);

    /**
     * Creates an int based on the given sequence of bytes.
     *
     * @param b
     *            the bytes
     * @return a long value
     */
    long createLong(final byte... b);

    /**
     * Returns some bytes from a given long value.
     *
     * @param l
     *            the long value
     * @param offset
     *            the offset byte to start at
     * @param length
     *            the length of bytes to retrieve. Default is 1.
     * @return the bytes
     */
    byte[] getBytesFrom(long l, int offset, int length);
}
