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
package ds2.oss.core.api;

/**
 * A persistence support contract.
 * 
 * @author dstrauss
 * @param <T>
 *            the entity type
 * @param <E>
 *            the entity primary key type
 * @version 0.2
 */
public interface PersistenceSupport<T extends Persistable<E>, E> {
    /**
     * Persists the given dto.
     * 
     * @param t
     *            the dto to persist.
     */
    void persist(T t);
    
    /**
     * Returns the object with the given id.
     * 
     * @param e
     *            the id
     * @return the found object, or null
     */
    T getById(E e);
}
