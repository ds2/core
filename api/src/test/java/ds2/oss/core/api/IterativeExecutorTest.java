package ds2.oss.core.api;

import ds2.oss.core.api.concurrent.IterativeExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

public class IterativeExecutorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final List<String> dataset = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    @Test
    public void testSchedule1() {
        LOGGER.info("start");
        IterativeExecutor.withValue(String.class).size(3).parallel(3).over((offset, size) -> {
            int lastOffset = offset + size;
            if (lastOffset > dataset.size()) {
                lastOffset = dataset.size();
            }
            if (offset > lastOffset) {
                return null;
            }
            return dataset.subList(offset, lastOffset);
        }).forEveryItem(s -> {
            LOGGER.info("Item here is {}", s);
        }).run();
        LOGGER.info("Stop");
    }
}
