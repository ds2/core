package ds2.oss.core.crypto.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.crypto.Argon2;
import ds2.oss.core.api.crypto.CoreCryptoException;
import ds2.oss.core.api.crypto.PasswordService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.enterprise.util.AnnotationLiteral;

import static org.testng.Assert.*;

public class Argon2PasswordServiceImplTest extends AbstractInjectionEnvironment {
    private PasswordService argon2To;

    @BeforeClass
    public void onClass() {
        argon2To = getInstance(PasswordService.class, new AnnotationLiteral<Argon2>() {
        });
    }

    @Test
    public void testCreateNull() throws CoreCryptoException {
        assertNull(argon2To.encryptPw(null));
    }

    @Test
    public void testCreateEmpty() throws CoreCryptoException {
        assertNull(argon2To.encryptPw("".toCharArray()));
    }

    @Test
    public void testCreate1() throws CoreCryptoException {
        String s = argon2To.encryptPw("test".toCharArray());
        assertNotNull(s);
        assertTrue(s.indexOf("argon2") > 0);
    }

    @Test
    public void testCreateAndValidate() throws CoreCryptoException {
        String s = argon2To.encryptPw("test235".toCharArray());
        assertNotNull(s);
        assertTrue(argon2To.isValidPassword(s, "test235".toCharArray()));
    }
}
