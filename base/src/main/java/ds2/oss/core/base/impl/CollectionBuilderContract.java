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
package ds2.oss.core.base.impl;

import java.util.Collection;

/**
 * Contract for the collection builder.
 *
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the collection type
 * @param <Z>
 *            the item type
 */
public interface CollectionBuilderContract<E extends Collection<Z>, Z> {
    /**
     * Adds an item to the collection.
     *
     * @param z
     *            the item
     * @return this instance
     */
    CollectionBuilderContract<E, Z> add(Z z);

    /**
     * Adds an item at a specific position.
     *
     * @param index
     *            the position
     * @param z
     *            the item
     * @return this instance
     */
    CollectionBuilderContract<E, Z> addAt(int index, Z z);

    /**
     * Whether to allow adding null values.
     *
     * @param b
     *            the flag value
     * @return this instance
     */
    CollectionBuilderContract<E, Z> allowNull(boolean b);

    /**
     * Creates the collection.
     *
     * @return the collection
     */
    E build();

    /**
     * Removes an item.
     *
     * @param z
     *            the item to remove
     * @return this instance
     */
    CollectionBuilderContract<E, Z> remove(Z z);
}
