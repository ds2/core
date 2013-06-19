package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.SymmetricKeyService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests.
 */
@Test(timeOut = 20000,groups = "sym")
public class SymKeyServiceImplTest extends WeldWrapper{
    private SymmetricKeyService to;

    @BeforeClass
    public void onClass(){
        to=getInstance(SymmetricKeyService.class);
    }
    @Test(groups = {"inject","sym"})
    public void testInject(){
        Assert.assertNotNull(to);
    }
}
