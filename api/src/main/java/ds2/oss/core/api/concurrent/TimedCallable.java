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

import ds2.oss.core.api.TaskExecutionTimedOutException;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class TimedCallable<V> implements Callable<V> {
    private static final Logger LOGGER = Logger.getLogger(TimedCallable.class.getName());
    private Callable<V> delegate;
    private long maxUnits;
    private TimeUnit timeUnit;

    public TimedCallable(Callable<V> delegate, long maxUnits, TimeUnit timeUnit) {
        this.delegate = delegate;
        this.maxUnits = maxUnits;
        this.timeUnit = timeUnit;
        if (maxUnits < 0) {
            throw new IllegalArgumentException("maxUnits must not be smaller than 0!");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("A timeUnit is required!");
        }
        if (delegate == null) {
            throw new IllegalArgumentException("You must give a delegate to execute!");
        }
    }

    @Override
    public V call() throws Exception {
        LOGGER.fine("Entering execution for timed callable on delegate..");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<V> delegateFuture = executorService.submit(delegate);
        LOGGER.log(Level.FINE, "Delegate is submitted. Trying to wait for $1 $2", new Object[]{maxUnits, timeUnit});
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(maxUnits, timeUnit)) {
                boolean cancelled = delegateFuture.cancel(true);
                LOGGER.log(Level.FINER, "Cancel request was: $1", cancelled);
                throw new TaskExecutionTimedOutException("We had to cancel the execution!");
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.FINE, "My wait was interrupted externally!", e);
            boolean cancelled = delegateFuture.cancel(true);
            LOGGER.log(Level.FINER, "Cancel request was: $1", cancelled);
            throw new TaskExecutionTimedOutException("This execution was stopped externally!", e);
        }
        V result = delegateFuture.get();
        LOGGER.log(Level.FINER, "Execution is done. Result is: $1", result);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimedCallable{");
        sb.append("maxUnits=").append(maxUnits);
        sb.append(", timeUnit=").append(timeUnit);
        sb.append('}');
        return sb.toString();
    }
}
