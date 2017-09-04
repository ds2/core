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
 * A persistence support contract. This can be a database, or a cache instance. Or ElasticSearch.
 *
 * @param <E>       the entity type
 * @param <PRIMKEY> the entity primary key type
 * @author dstrauss
 * @version 0.2
 */
public interface PersistenceSupport<E extends IdAware<PRIMKEY>, PRIMKEY> {
    /**
     * Returns the object with the given id.
     *
     * @param e the id
     * @return the found object, or null
     */
    E getById(PRIMKEY e);

    /**
     * Persists the given dto.
     *
     * @param t the dto to persist.
     */
    void persist(E t);

    void deleteById(PRIMKEY id);

}
