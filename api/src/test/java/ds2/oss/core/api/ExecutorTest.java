package ds2.oss.core.api;

import ds2.oss.core.api.concurrent.TimedCallable;
import org.testng.annotations.Test;

import java.util.concurrent.*;

import static org.testng.Assert.*;

public class ExecutorTest {
    @Test(expectedExceptions = ExecutionException.class)
    public void testRuntime() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> thisFuture = executorService.submit(new TimedCallable<>(() -> {
            System.out.println("Hello world");
            Thread.sleep(700);
            System.out.println("Hello world 2");
            return -1;
        }, 500, TimeUnit.MILLISECONDS));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        assertNotNull(thisFuture);
        assertFalse(thisFuture.isCancelled(), "The internal task is canceled but shoult not.");
        assertTrue(thisFuture.isDone(), "The internal task is finished but should not.");
        assertNull(thisFuture.get());
    }
}
