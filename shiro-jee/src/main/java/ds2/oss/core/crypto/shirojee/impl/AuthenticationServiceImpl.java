package ds2.oss.core.crypto.shirojee.impl;

import ds2.oss.core.crypto.shirojee.api.AuthenticationService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Dependent
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private Subject subject;

    @Override
    public boolean authenticateBasic(String username, char[] pw) {
        LOG.debug("Try to authenticate user {} with pw using subject {}", username, subject);
        //Subject subject = SecurityUtils.getSubject();
        boolean rc = false;
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken plainToken = new UsernamePasswordToken(username, pw);
            try {
                subject.login(plainToken);
                rc = true;
            } catch (UnknownAccountException uae) {
                LOG.debug("This account is unknown!", uae);
            } catch (IncorrectCredentialsException ice) {
                LOG.debug("The given credential is wrong!", ice);
            } catch (LockedAccountException lae) {
                LOG.debug("This account is locked!", lae);
            } catch (AuthenticationException ae) {
                LOG.debug("An auth error occurred!", ae);
            }
        } else {
            LOG.debug("{} is already authenticated. Nothing to do here.");
            rc = true;
        }
        return rc;
    }

    @Override
    public List<String> getRolesOfUser(String username, char[] pw) {
        return null;
    }

    @Override
    public boolean hasPermission(String permission) {
        LOG.debug("Checking permission {}", permission);
        boolean rc = false;
        try {
            LOG.debug("Principals of this subject are: {}", subject.getPrincipals());
            subject.checkPermission(permission);
            rc = true;
        } catch (AuthorizationException e) {
            LOG.debug("the given permission is wrong for this subject!", e);
        }
        return rc;
    }

    @Override
    public boolean isInRole(String role) {
        LOG.debug("Checking for role {}", role);
        boolean rc = false;
        try {
            rc = subject.hasRole(role);
        } catch (AuthorizationException e) {
            LOG.debug("the given permission is wrong for this subject!", e);
        }
        return rc;
    }

    @Override
    public void logout() {
        LOG.debug("Logging out the subject from this thread!");
        subject.logout();
    }
}
