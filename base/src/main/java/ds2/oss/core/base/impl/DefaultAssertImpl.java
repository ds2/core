package ds2.oss.core.base.impl;

import ds2.oss.core.api.Assert;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

/**
 * Created by deindesign on 17.03.16.
 */
@Alternative
@Dependent
@Priority(1)
public class DefaultAssertImpl extends AbstractAssertImpl implements Assert {
    @Override
    protected RuntimeException createException(String msg) {
        return new IllegalArgumentException(msg);
    }
}
