package ds2.oss.core.crypto.shirojee.impl;

import ds2.oss.core.crypto.shirojee.api.RealmProvider;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.invoke.MethodHandles;

@Singleton
public class EnvironmentLoader {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private RealmProvider realmProvider;

    @Produces
    public SecurityManager createSecurityManager() {
        LOG.debug("Preparing a security manager here..");
        SecurityManager securityManager = new DefaultSecurityManager(realmProvider.getRealms());
//Make the SecurityManager instance available to the entire application via static memory:
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSecurityManager();
    }

    @Produces
    public Subject createSubject() {
        LOG.debug("Preparing a subject here..");
        createSecurityManager();
        return SecurityUtils.getSubject();
    }

    @Produces
    public Session createSession() {
        LOG.debug("Preparing a session here..");
        return createSubject().getSession();
    }
}
