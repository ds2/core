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
/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

/**
 * Contract to access an elastic search node.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface ElasticSearchService {
    /**
     * Puts an object into the index.
     * 
     * @param index
     *            The index name to put the object into
     * 
     * @param t
     *            the object to put
     * @param codec
     *            the codec to use
     * @param <T>
     *            the type to put
     * @return the object
     */
    <T> T put(String index, T t, TypeCodec<T> codec);

    void refreshIndexes(String... indexes);
}
