package ds2.oss.core.base.impl;

import ds2.oss.core.api.CoreConfiguration;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

/**
 * Created by deindesign on 21.03.16.
 */
@Alternative
@ApplicationScoped
@Priority(1)
public class DefaultCoreConfiguration implements CoreConfiguration {
    @Override
    public int getMethodLockTimeout() {
        return 10;
    }
}
