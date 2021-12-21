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
package ds2.oss.core.api;

import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * This contract is to be attached to typical service contracts in the backend/deep core layer.
 * Typically, this interface is attached to entity services.
 */
public interface JpaCrudFacade<PRIMKEY, E extends IdAware<PRIMKEY>> extends PersistenceSupport<E, PRIMKEY> {
    List<E> getAll(int offset, int size);

    List<E> getAllByQuery(int offset, int size, TypedQuery<E> query);

}
