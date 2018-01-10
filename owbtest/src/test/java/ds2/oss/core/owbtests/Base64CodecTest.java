package ds2.oss.core.owbtests;

import ds2.oss.core.api.Base64Codec;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dstrauss on 19.04.17.
 */
public class Base64CodecTest extends AbstractOwbInjectionEnvironment {
    private Base64Codec to;

    @BeforeClass
    public void onClass() {
        to = getInstance(Base64Codec.class);
    }

    @Test
    public void testExists() {
        Assert.assertNotNull(to);
    }
}
