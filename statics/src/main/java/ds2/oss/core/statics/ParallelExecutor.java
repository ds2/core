/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by dstrauss on 25.04.17.
 */
public class ParallelExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ExecutorService executor;

    private ParallelExecutor(int i) {
        LOG.debug("Creating pool with {} threads..", i);
        executor = Executors.newFixedThreadPool(i);
    }

    public static ParallelExecutor withThreads(int initThreadCount) {
        return new ParallelExecutor(initThreadCount);
    }

    public ParallelExecutor add(Callable<?> callable) {
        LOG.debug("Submitting this callable: {}", callable);
        executor.submit(callable);
        return this;
    }

    public ParallelExecutor add(Runnable callable) {
        executor.submit(callable);
        return this;
    }

    /**
     * Will stop adding new tasks. Any currently submitted tasks will still be executed.
     *
     * @return this
     */
    public ParallelExecutor scheduleStop() {
        LOG.debug("Scheduling shutdown of executor..");
        executor.shutdown();
        return this;
    }

    /**
     * Will stop adding new tasks and wait some time until either all tasks are done, or up to the given time.
     *
     * @param timeout a time value, depends on the unit
     * @param unit    the time unit
     * @return this
     * @throws InterruptedException if the wait was interrupted.
     */
    public ParallelExecutor waitForFinish(long timeout, TimeUnit unit) throws InterruptedException {
        LOG.debug("Scheduling shutdown in {} {}", timeout, unit);
        executor.shutdown();
        executor.awaitTermination(timeout, unit);
        return this;
    }

    /**
     * Will stop adding new tasks and wait some time until either all tasks are done, or up to the given time.
     *
     * @param timeout the seconds to wait
     * @return this
     * @throws InterruptedException if the wait was interrupted.
     */
    public ParallelExecutor waitForFinishTheseSeconds(long timeout) throws InterruptedException {
        return waitForFinish(timeout, TimeUnit.SECONDS);
    }
}
