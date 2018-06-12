package ds2.oss.core.statics;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dstrauss on 25.04.17.
 */
public class ParallelExecutorTest {
    @Test
    public void testParallel1() throws InterruptedException {
        ParallelExecutor.withThreads(1).add(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Hello");
            } catch (InterruptedException e) {
            }
        }).add(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("Hello 2");
            } catch (InterruptedException e) {
            }
        }).add(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hello 3");
            } catch (InterruptedException e) {
            }
        }).waitForFinishTheseSeconds(5);
        Assert.assertTrue(true);
    }

    @Test
    public void testParallel2() throws InterruptedException {
        ParallelExecutor.withThreads(5).add(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Hello");
            } catch (InterruptedException e) {
            }
        }).add(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("Hello 2");
            } catch (InterruptedException e) {
            }
        }).add(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hello 3");
            } catch (InterruptedException e) {
            }
        }).waitForFinishTheseSeconds(5);
        Assert.assertTrue(true);
    }
}
