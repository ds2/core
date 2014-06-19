/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.codec.boon;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author dstrauss
 */
public class BoonJsonCodecTest extends AbstractInjectionEnvironment {

    private JsonCodec to;

    @BeforeClass
    public void onClass() {
        to = getInstance(JsonCodec.class);
    }

    @Test
    public void testEncodeNull() throws CoreException {
        Assert.assertEquals(to.encode(null), "[]");
    }
}
