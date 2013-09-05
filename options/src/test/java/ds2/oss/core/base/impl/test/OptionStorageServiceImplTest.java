/**
 * 
 */
package ds2.oss.core.base.impl.test;

import org.testng.annotations.Test;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionStorageService;

/**
 * The test.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class OptionStorageServiceImplTest implements Options {
    /**
     * The test object.
     */
    private OptionStorageService<Long> to;
    
    @Test
    public void testCreateOption() {
        final Option<Long, String> option = to.createOption(USERNAME, "dummy");
        // Assert.assertNotNull(option);
    }
    
}
