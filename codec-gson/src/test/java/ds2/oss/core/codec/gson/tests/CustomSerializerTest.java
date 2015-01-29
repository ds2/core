/**
 * 
 */
package ds2.oss.core.codec.gson.tests;

import ds2.oss.core.api.CodecException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @author dstrauss
 *
 */
public class CustomSerializerTest extends AbstractInjectionEnvironment {
    
    private JsonCodec to;
    
    @BeforeClass
    public void onClass() {
        to = getInstance(JsonCodec.class);
    }

    @Test
    public void testDate() throws CodecException {
        String rc=to.encode(new Date());
        Assert.assertEquals(rc, "\"datum\"");
    }
}
