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
package ds2.oss.core.api.concurrent;

import java.util.List;

@FunctionalInterface
public interface ListsIterator<E> {
    /**
     * Returns a list of items from the given query.
     *
     * @param startOffset the start offset. Starts with 0 ;)
     * @param count       the count of items to return
     * @return the found items, or null or empty list
     * @throws RetrievalException if there is an issue getting the items
     */
    List<E> get(int startOffset, int count) throws RetrievalException;
}
