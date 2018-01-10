package ds2.oss.core.crypto.test;

import ds2.oss.core.api.crypto.CoreCryptoException;
import ds2.oss.core.api.crypto.PasswordService;
import ds2.oss.core.api.crypto.Pbkdf2;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.enterprise.util.AnnotationLiteral;

import static org.testng.Assert.*;

public class Pbkdf2PasswordServiceImplTest extends AbstractInjectionEnvironment {
    private PasswordService pbkdf2To;

    @BeforeClass
    public void onClass() {
        pbkdf2To = getInstance(PasswordService.class, new AnnotationLiteral<Pbkdf2>() {
        });
    }

    @Test
    public void testCreateNull() throws CoreCryptoException {
        assertNull(pbkdf2To.encryptPw(null));
    }

    @Test
    public void testCreateEmpty() throws CoreCryptoException {
        assertNull(pbkdf2To.encryptPw("".toCharArray()));
    }

    @Test
    public void testCreate1() throws CoreCryptoException {
        String s = pbkdf2To.encryptPw("test".toCharArray());
        assertNotNull(s);
        assertTrue(s.indexOf("pbkdf2") >= 0);
    }

    @Test
    public void testCreateAndValidate() throws CoreCryptoException {
        String s = pbkdf2To.encryptPw("test".toCharArray());
        assertNotNull(s);
        assertTrue(pbkdf2To.isValidPassword(s, "test".toCharArray()));
    }
}
