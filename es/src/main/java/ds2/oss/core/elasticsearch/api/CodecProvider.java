/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.elasticsearch.api;

/**
 * The codec provider.
 *
 * @author dstrauss
 * @version 0.2
 */
public interface CodecProvider {
    /**
     * Finds a codec for the given class type.
     *
     * @param c
     *            the class type
     * @param <T>
     *            the type
     * @return the codec, or null if not found
     */
    <T> TypeCodec<T> findFor(Class<T> c);

    /**
     * Dummy method to get the count of found instances.
     *
     * @return 0
     */
    int getInstanceCount();
}
