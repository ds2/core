package ds2.oss.core.crypto.shirojee;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyRealm implements Realm, Authorizer {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private String name;

    public MyRealm() {
        this.name = "TestRealm1";
    }

    @Override
    public String getName() {
        return name;
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
        LOG.debug("Token to check is {}", token);
        if (token == null) {
            throw new AuthenticationException("No token given!");
        }
        MyAuthInfo authInfo = new MyAuthInfo();
        if (authInfo.isLocked()) {
            throw new LockedAccountException("This account is locked!");
        }
        if (authInfo.isCredentialsExpired()) {
            throw new ExpiredCredentialsException("The credentials expired!");
        }
        SimpleAccount account = null;
        String user = token.getPrincipal().toString();
        if ("myuser".equalsIgnoreCase(user)) {
            Set<String> roleNames = CollectionUtils.asSet("admin", "writer", "author");
            account = new SimpleAccount("myuser", "mypw", getName(), roleNames, null);
        } else {
            throw new UnknownAccountException();
        }
        LOG.debug("returning authInfo: {}", account);
        return account;
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        return false;
    }

    @Override
    public boolean isPermitted(PrincipalCollection subjectPrincipal, Permission permission) {
        return false;
    }

    @Override
    public boolean[] isPermitted(PrincipalCollection subjectPrincipal, String... permissions) {
        return new boolean[0];
    }

    @Override
    public boolean[] isPermitted(PrincipalCollection subjectPrincipal, List<Permission> permissions) {
        return new boolean[0];
    }

    @Override
    public boolean isPermittedAll(PrincipalCollection subjectPrincipal, String... permissions) {
        return false;
    }

    @Override
    public boolean isPermittedAll(PrincipalCollection subjectPrincipal, Collection<Permission> permissions) {
        return false;
    }

    @Override
    public void checkPermission(PrincipalCollection subjectPrincipal, String permission) throws AuthorizationException {

    }

    @Override
    public void checkPermission(PrincipalCollection subjectPrincipal, Permission permission) throws AuthorizationException {

    }

    @Override
    public void checkPermissions(PrincipalCollection subjectPrincipal, String... permissions) throws AuthorizationException {

    }

    @Override
    public void checkPermissions(PrincipalCollection subjectPrincipal, Collection<Permission> permissions) throws AuthorizationException {

    }

    @Override
    public boolean hasRole(PrincipalCollection subjectPrincipal, String roleIdentifier) {
        LOG.debug("hasRole: {}, with role={}", subjectPrincipal, roleIdentifier);
        boolean rc = false;
        if (!subjectPrincipal.isEmpty()) {
            Collection<String> ourPrincipalsCol = subjectPrincipal.fromRealm(name);
            if (ourPrincipalsCol != null && !ourPrincipalsCol.isEmpty()) {
                for (String s : ourPrincipalsCol) {
                    if ("myuser".equalsIgnoreCase(s)) {
                        if ("writer".equalsIgnoreCase(roleIdentifier)) {
                            rc = true;
                            break;
                        }
                    }
                }
            }
        }
        LOG.debug("Returning hasRole: {}", rc);
        return rc;
    }

    @Override
    public boolean[] hasRoles(PrincipalCollection subjectPrincipal, List<String> roleIdentifiers) {
        return new boolean[0];
    }

    @Override
    public boolean hasAllRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers) {
        return false;
    }

    @Override
    public void checkRole(PrincipalCollection subjectPrincipal, String roleIdentifier) throws AuthorizationException {

    }

    @Override
    public void checkRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers) throws AuthorizationException {

    }

    @Override
    public void checkRoles(PrincipalCollection subjectPrincipal, String... roleIdentifiers) throws AuthorizationException {

    }
}
