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
package ds2.oss.core.options.api;

/**
 * Idea for a value codec contract.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the value type
 */
public interface ValueCodec<V> {
    /**
     * Converts the given object into a string representation.
     * 
     * @param v
     *            the value object
     * @return the string value
     */
    String toString(V v);
    
    /**
     * Converts a given string into a value object.
     * 
     * @param s
     *            the string
     * @return the value object
     */
    V toValue(String s);
}
