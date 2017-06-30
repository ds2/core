package ds2.oss.core.base.impl.test;

import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by dstrauss on 09.02.17.
 */
public class RandomProviderTest extends AbstractInjectionEnvironment {
    private Random random;

    @BeforeClass
    public void onInit() {
        random = getInstance(Random.class);
    }

    @Test
    public void testExist() {
        Assert.assertNotNull(random);
    }
}
