package ds2.oss.core.elasticsearch.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.elasticsearch.api.CodecProvider;
import ds2.oss.core.elasticsearch.test.dto.CountryDto;

/**
 * A test for the provider.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class CodecProviderImplTest extends AbstractInjectionEnvironment {
    /**
     * The test object.
     */
    private CodecProvider to;
    
    @BeforeClass
    public void onClass() {
        to = getInstance(CodecProvider.class);
    }
    
    @Test
    public void existTest() {
        Assert.assertTrue(to.getInstanceCount() > 0);
    }
    
    @Test
    public void testNull() {
        Assert.assertNull(to.findFor(null));
    }
    
    @Test
    public void test1() {
        Assert.assertNotNull(to.findFor(CountryDto.class));
    }
}
