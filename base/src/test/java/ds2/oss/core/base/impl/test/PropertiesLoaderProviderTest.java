package ds2.oss.core.base.impl.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by deindesign on 16.03.16.
 */
public class PropertiesLoaderProviderTest extends AbstractInjectionEnvironment {
    private PropsLoaderDto to;

    @BeforeClass
    public void onClass() {
        to = getInstance(PropsLoaderDto.class);
    }

    @Test
    public void testVal() {
        Assert.assertEquals(to.getValue(), "Hello, world :D");
    }

}
