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

import java.util.Map;

/**
 * A contract for a codec.
 * 
 * @author dstrauss
 * @param <T>
 *            The type to encode or decode
 * @version 0.2
 */
public interface TypeCodec<T> {
    /**
     * Converts a given dto into a JSON variant.
     * 
     * @param t
     *            the dto to convert
     * @return the JSON string to use for indexing
     */
    String toJson(T t);
    
    /**
     * Converts a indexed document into a dto.
     * 
     * @param o
     *            the map of fields
     * @return the dto to use
     */
    T toDto(Map<String, Object> o);
    
    /**
     * Returns the type name to use for this type of DTO.
     * 
     * @return the index type name, or null if not set.
     */
    String getIndexTypeName();
    
    /**
     * Returns the index name to primarily use.
     * 
     * @return the default index name.
     */
    String getIndexName();
    
    /**
     * Returns the JSON mapping for this DTO.
     * 
     * @return the JSON mapping, or null if not set
     */
    String getMapping();
}
