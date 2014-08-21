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
package ds2.oss.core.elasticsearch.api;

import java.util.Map;

/**
 * A contract for a codec.
 * 
 * @param <T>
 *            The type to encode or decode
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface TypeCodec<T> {
    /**
     * Converts a given dto into a JSON variant.
     * 
     * @param t
     *            the dto to convert
     * 
     * @return the JSON string to use for indexing
     */
    String toJson(T t);
    
    /**
     * Returns the type name to use for this type of DTO.
     * 
     * @return the index type name, or null if not set.
     */
    String getIndexTypeName();
    
    /**
     * Returns the JSON mapping for this DTO.
     * 
     * @return the JSON mapping, or null if not set
     */
    String getMapping();
    
    /**
     * Should a refresh operation be done on inserting?
     * 
     * @return TRUE or FALSE, default is FALSE
     */
    boolean refreshOnIndexing();
    
    /**
     * Flag to indicate write a new document to all nodes within the cluster.
     * 
     * @return TRUE or FALSE, default is FALSE meaning that the document is written to at least two
     *         nodes, replication is done in the background for the other nodes.
     */
    boolean replicateOnIndexing();
    
    /**
     * A simple check if instances of the given class can be used with this codec.
     * 
     * @param c
     *            the class. Usually a dto that this codec can deal with.
     * @return TRUE if this codec can deal with instances of this class, otherwise and by default:
     *         FALSE.
     */
    boolean matches(Class<?> c);
    
    /**
     * Converts a given json content into a dto.
     * 
     * @param jsonContent
     *            the json content
     * @return the dto
     */
    T toDto(String jsonContent);
    
    /**
     * An alternate way to create a dto based on some fields.
     * 
     * @param fields
     *            the fields
     * @return the dto
     */
    T toDto(Map<String, Object> fields);
}
