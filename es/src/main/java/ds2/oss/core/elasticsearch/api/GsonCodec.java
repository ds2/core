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
package ds2.oss.core.elasticsearch.api;

/**
 * A codec contract to deal with JSON strings via Gson.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface GsonCodec {
    /**
     * Encodes a given type into a json.
     * 
     * @param t
     *            the type
     * @param <T>
     *            the dto type
     * @return the json to use, or null if an error occurred.
     */
    <T> String encode(T t);
    
    /**
     * Decodes a json string into a type.
     * 
     * @param c
     *            the class of the type
     * @param json
     *            the json string
     * @param <T>
     *            the dto type
     * @return the created type, or null if an error occurred
     */
    <T> T decode(Class<T> c, String json);
}
