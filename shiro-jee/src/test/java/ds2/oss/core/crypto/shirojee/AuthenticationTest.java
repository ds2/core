package ds2.oss.core.crypto.shirojee;

import ds2.oss.core.crypto.shirojee.api.AuthenticationService;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AuthenticationTest extends AbstractInjectionEnvironment {
    private AuthenticationService authenticationService;

    @BeforeClass
    public void onLoad() {
        authenticationService = getInstance(AuthenticationService.class);
    }

    @Test
    public void auth1() {
        boolean b = authenticationService.authenticateBasic("root", "rootPassword".toCharArray());
        assertTrue(b);
    }
}
