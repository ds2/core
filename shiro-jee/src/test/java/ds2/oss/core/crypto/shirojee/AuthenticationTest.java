package ds2.oss.core.crypto.shirojee;

import ds2.oss.core.crypto.shirojee.api.AuthenticationService;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.activator.ActivateRequestContext;
import org.jboss.weld.context.unbound.UnboundLiteral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class AuthenticationTest extends AbstractInjectionEnvironment {
    private AuthenticationService authenticationService;

    @BeforeClass
    public void onLoad() {
        RequestContext requestContext = getWeldContainer().instance().select(RequestContext.class, UnboundLiteral.INSTANCE).get();
        requestContext.activate();
        authenticationService = getInstance(AuthenticationService.class);
    }

    @Test
    @ActivateRequestContext
    public void auth1() {
        boolean b = authenticationService.authenticateBasic("root", "rootPassword".toCharArray());
        assertTrue(b);
    }

    @AfterMethod
    public void afterMethod() {
        authenticationService.logout();
    }

    @Test
    @ActivateRequestContext
    public void auth2() {
        boolean b = authenticationService.authenticateBasic("jsmith", "jsmithPassword".toCharArray());
        assertTrue(b);
    }

    @Test
    @ActivateRequestContext
    public void authorize1() {
        assertTrue(authenticationService.authenticateBasic("jsmith", "jsmithPassword".toCharArray()));
        assertTrue(authenticationService.isInRole("engineer"));
        assertTrue(authenticationService.hasPermission("file:read:/usr/local/tomcat/bin/startup.sh"), "Permission is wrong!");
    }

    public void testRoles() {
        List<String> roles = authenticationService.getRolesOfUser("root", "rootPassword".toCharArray());
    }
}
