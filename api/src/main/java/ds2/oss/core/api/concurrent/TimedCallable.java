package ds2.oss.core.api.concurrent;

import ds2.oss.core.api.TaskExecutionTimedOutException;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
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
