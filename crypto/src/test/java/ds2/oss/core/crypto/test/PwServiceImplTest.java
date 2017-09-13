package ds2.oss.core.crypto.test;

import ds2.oss.core.api.crypto.CoreCryptoException;
import ds2.oss.core.api.crypto.PasswordService;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class PwServiceImplTest extends AbstractInjectionEnvironment {
    private PasswordService to;

    @BeforeClass
    public void onClass() {
        to = getInstance(PasswordService.class);
    }

    @Test
    public void testCreateNull() throws CoreCryptoException {
        assertNull(to.encryptPw(null));
    }

    @Test
    public void testCreateEmpty() throws CoreCryptoException {
        assertNull(to.encryptPw("".toCharArray()));
    }

    @Test
    public void testCreate1() throws CoreCryptoException {
        String s = to.encryptPw("test".toCharArray());
        assertNotNull(s);
    }
}
