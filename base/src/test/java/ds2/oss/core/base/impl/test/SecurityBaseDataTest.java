package ds2.oss.core.base.impl.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.base.impl.AlternateSecurityBaseDataImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by dstrauss on 30.06.17.
 */
public class SecurityBaseDataTest extends AbstractInjectionEnvironment {
    private SecurityBaseData to;

    @BeforeClass
    public void onClass() {
        to = getInstance(SecurityBaseData.class);
        assertTrue(to instanceof AlternateSecurityBaseDataImpl);
    }

    @Test
    public void testInitVector() {
        byte[] bytes = to.getInitVector();
        assertNotNull(bytes);
    }

    @Test
    public void testSalt() {
        byte[] salt = to.getSalt();
        assertNotNull(salt);
    }
}
