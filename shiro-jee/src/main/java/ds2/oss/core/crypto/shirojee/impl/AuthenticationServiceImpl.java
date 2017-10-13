package ds2.oss.core.crypto.shirojee.impl;

import ds2.oss.core.crypto.shirojee.api.AuthenticationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;

@Dependent
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private Subject subject;
    @Inject
    private SecurityManager securityManager;

    @Override
    public boolean authenticateBasic(String username, char[] pw) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken plainToken = new UsernamePasswordToken(username, pw);
            try {
                subject.login(plainToken);
                return true;
            } catch (UnknownAccountException uae) {
                //username wasn't in the system, show them an error message?
            } catch (IncorrectCredentialsException ice) {
                //password didn't match, try again?
            } catch (LockedAccountException lae) {
                //account for that username is locked - can't login.  Show them a message?
            } catch (AuthenticationException ae) {
                //unexpected condition - error?
            }
        }
        return false;
    }
}
