package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.SystemPropertyService;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

/**
 * Created by deindesign on 08.04.16.
 */
public class SystemPropertyServiceImplTest extends AbstractInjectionEnvironment {
    private static final Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private SystemPropertyService to;

    @BeforeClass
    public void onClass(){
        to=getInstance(SystemPropertyService.class);
    }

    @Test
    public void testGetAllProps(){
        Properties props=to.getAllSysProps();
        LOG.info("Known props: {}",props);
        Assert.assertNotNull(props);
        Assert.assertNotNull(props.getProperty("java.io.tmpdir"));
    }
}
