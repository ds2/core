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

import java.util.List;

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
     * @param t
     *            the object to put
     * @param codec
     *            the codec to use
     * @param <T>
     *            the type to put
     * 
     * @return the object
     */
    <T> T put(String index, T t, TypeCodec<T> codec);
    
    /**
     * Loads a dto by the given id. It is assumed that there is a known codec for this type.
     * 
     * @param index
     *            the index name
     * @param c
     *            the class type
     * @param id
     *            the id of the document
     * @param <T>
     *            the type of the dto
     * 
     * @return the dto, or null if not found
     */
    <T> T get(String index, Class<T> c, String id);
    
    /**
     * Refreshes the given indexes.
     * 
     * @param indexes
     *            the index names
     * @return TRUE if successful, otherwise FALSE
     */
    boolean refreshIndexes(String... indexes);
    
    /**
     * Deletes all given indexes.
     * 
     * @param indexes
     *            the indexes to delete
     */
    void deleteIndexes(String... indexes);
    
    /**
     * This will load any JSON files from the given class type and return a list of it. This relies
     * on JSON files being in the package folder, having the syntax
     * <i>CLASSNAME-elasticsearch.insert.KEY.json</i>.
     * 
     * @param c
     *            the class (which has a package and a name)
     * @param <T>
     *            the type of the dto
     * 
     * @return the data
     */
    <T> List<T> getDefaultData(Class<T> c);
    
    /**
     * This will load any known JSON files for the given class type into the index. It is expected
     * that there is a codec for it. Basically, it will load all found JSON files and perform a bulk
     * insert.
     * 
     * @param index
     *            the index name
     * @param <T>
     *            the type of the dto
     * 
     * @param c
     *            the class type
     * @return TRUE of the operation was successful, otherwise FALSE
     */
    <T> boolean insertDefaultData(String index, Class<T> c);
}
