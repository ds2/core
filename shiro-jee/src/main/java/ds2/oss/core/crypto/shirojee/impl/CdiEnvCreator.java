package ds2.oss.core.crypto.shirojee.impl;

import ds2.oss.core.crypto.shirojee.api.RealmProvider;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import java.lang.invoke.MethodHandles;

public class CdiEnvCreator extends EnvironmentLoaderListener {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private RealmProvider realmProvider;

    @Override
    protected WebEnvironment createEnvironment(ServletContext sc) {
        LOG.debug("Requesting environment for context {}", sc);
        return super.createEnvironment(sc);
    }
}
