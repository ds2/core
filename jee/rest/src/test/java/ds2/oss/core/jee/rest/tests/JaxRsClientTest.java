package ds2.oss.core.jee.rest.tests;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dstrauss on 21.02.17.
 */
public class JaxRsClientTest extends AbstractInjectionEnvironment {
    private MySessionScopedService to;

    @BeforeClass
    public void onClass() {
        to = getInstance(MySessionScopedService.class);
    }

    @Test
    public void testCreated() {
        Assert.assertNotNull(to);
    }
}
