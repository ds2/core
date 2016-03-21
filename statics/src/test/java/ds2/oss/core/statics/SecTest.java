package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created by deindesign on 18.03.16.
 */
public class SecTest {
    private static final Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void testProviders(){
        List<String> providerNames=Securitix.getCurrentSecurityProviders();
        Assert.assertNotNull(providerNames);
        Assert.assertTrue(providerNames.size()>0);
        LOG.info("Found providers: {}", providerNames);
    }
}
