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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An executor builder to parallelize executions.
 *
 * @param <E> the type of dto to handle
 */
public class IterativeExecutor<E> implements IterativeExecutorConfiguration<E> {
    private static final Logger LOGGER = Logger.getLogger(IterativeExecutor.class.getName());
    private int pageSize = 10;
    private ListsIterator<E> listsIterator;
    private int maxThreads = 1;
    private long sleep = 0L;
    private int startingOffset = 0;
    private ExecutorService executorService;
    private Consumer<E> task;
    private long await = 0;

    private IterativeExecutor() {
        //nothing special to do
        super();
    }

    public static <E> IterativeExecutorConfiguration<E> withValue(Class<E> c) {
        LOGGER.fine("Creating executor with value class " + c.getName());
        return new IterativeExecutor<>();
    }

    @Override
    public IterativeExecutorConfiguration<E> size(int i) {
        LOGGER.finer("Setting the page count to " + i);
        pageSize = i;
        return this;
    }

    @Override
    public IterativeExecutorConfiguration<E> awaitTermination(long b) {
        this.await = b;
        return this;
    }

    @Override
    public IterativeExecutorConfiguration<E> over(ListsIterator<E> listsIterator) {
        this.listsIterator = listsIterator;
        return this;
    }

    @Override
    public IterativeExecutorConfiguration<E> parallel(int p) {
        maxThreads = p;
        return this;
    }

    @Override
    public void run() {
        LOGGER.fine("t1");
        synchronized (this) {
            LOGGER.fine("Starting forEach task..");
            if (executorService == null) {
                executorService = Executors.newFixedThreadPool(maxThreads);
            }
            while (true) {
                try {
                    LOGGER.log(Level.FINER, "Param test: offset {0} with size={1}", new Object[]{startingOffset, pageSize});
                    List<E> resultItems = listsIterator.get(startingOffset, pageSize);
                    if (resultItems == null || resultItems.isEmpty()) {
                        LOGGER.fine("Result query is empty, so we will break here.");
                        break;
                    }
                    startingOffset += pageSize;
                    resultItems.forEach(item -> executorService.submit(() -> task.accept(item)));
                    if (sleep > 0) {
                        try {
                            Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                            LOGGER.log(Level.FINE, "Error when sleeping ;) ", e);
                        }
                    }
                } catch (RetrievalException e) {
                    LOGGER.log(Level.WARNING, "Error when getting data from the retrieval process!", e);
                }
            }
            LOGGER.fine("Done. Shutting down executor..");
            executorService.shutdown();
            if (await > 0) {
                try {
                    executorService.awaitTermination(await, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.WARNING, "Error when stopping the executor service!", e);
                }
            }
            LOGGER.fine("Done with execution. Thx.");
        }
        LOGGER.fine("t1e");
    }

    @Override
    public IterativeExecutorConfiguration<E> sleepBetweenQueries(long seconds) {
        this.sleep = seconds;
        return this;
    }

    @Override
    public IterativeExecutorConfiguration<E> forEveryItem(Consumer<E> task) {
        this.task = task;
        return this;
    }
}
