package ds2.oss.core.crypto.shirojee;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class MyRealm implements Realm {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public String getName() {
        return "TestRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        boolean rc = false;
        if (token == null) {
            return false;
        }
        if (token instanceof UsernamePasswordToken) {
            rc = true;
        }
        return rc;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            throw new AuthenticationException("No token given!");
        }
        MyAuthInfo authInfo = new MyAuthInfo();

        return authInfo;
    }
}
