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

import java.util.function.Consumer;

public interface IterativeExecutorConfiguration<E> extends Runnable {
    /**
     * Sets the result size. Often called page size.
     *
     * @param i the max size of items to return per query.
     * @return the configurator
     */
    IterativeExecutorConfiguration<E> size(int i);

    /**
     * Whether to wait for a termination, or not.
     *
     * @param seconds the seconds to wait
     * @return the configurator
     */
    IterativeExecutorConfiguration<E> awaitTermination(long seconds);

    /**
     * How many parallel threads to run.
     *
     * @param i the thread count
     * @return the runner
     */
    IterativeExecutorConfiguration<E> parallel(int i);

    /**
     * Sets the iterator method to use.
     *
     * @param listsIterator the iterator method
     * @return the forEach runner
     */
    IterativeExecutorConfiguration<E> over(ListsIterator<E> listsIterator);

    /**
     * Starts the execution.
     *
     * @param task the task that should be invoked for each item
     */
    IterativeExecutorConfiguration<E> forEveryItem(Consumer<E> task);

    /**
     * Sets the seconds to sleep between queries.
     *
     * @param seconds the seconds to sleep. Defaults to 0.
     * @return the executor
     */
    IterativeExecutorConfiguration<E> sleepBetweenQueries(long seconds);
}
