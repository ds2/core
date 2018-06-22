/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.crypto.shirojee.impl;

import ds2.oss.core.crypto.shirojee.api.RealmProvider;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
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
    @ApplicationScoped
    public SecurityManager createSecurityManager() {
        try {
            LOG.debug("Preparing a security manager here..");
            return SecurityUtils.getSecurityManager();
        } catch (UnavailableSecurityManagerException e) {
            LOG.debug("SecManager not yet available. Try to create one from the realm provider..");
            SecurityManager securityManager = new DefaultSecurityManager(realmProvider.getRealms());
            SecurityUtils.setSecurityManager(securityManager);
            return createSecurityManager();
        }
    }

    @Produces
    @RequestScoped
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
