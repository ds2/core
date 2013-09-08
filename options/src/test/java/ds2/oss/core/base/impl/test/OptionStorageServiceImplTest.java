/**
 * 
 */
package ds2.oss.core.base.impl.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;

/**
 * The test.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class OptionStorageServiceImplTest extends AbstractInjectionEnvironment implements Options {
    /**
     * The test object.
     */
    private NumberedOptionStorageService to;
    
    @BeforeClass(alwaysRun = true)
    public void onClass() {
        to = getInstance(NumberedOptionStorageService.class);
    }
    
    @Test
    public void testCreateOption() {
        final Option<Long, String> option = to.createOption(USERNAME, "dummy");
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
    }
    
}
