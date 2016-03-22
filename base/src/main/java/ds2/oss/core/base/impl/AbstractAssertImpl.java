package ds2.oss.core.base.impl;

import ds2.oss.core.api.AssertHelper;

import java.util.Collection;

/**
 * Created by deindesign on 17.03.16.
 */
public abstract class AbstractAssertImpl implements AssertHelper {
    protected abstract RuntimeException createException(String msg);

    @Override
    public void assertNotNull(Object o, String errorMsg) {
        if(o==null){
            throw createException(errorMsg);
        }
    }

    @Override
    public void assertNotEmpty(String s, String errorMsg) {
        if(s==null||s.trim().length()==0){
            throw createException(errorMsg);
        }
    }

    @Override
    public void assertNotEmpty(Collection<?> c, String errorMsg) {
        if(c==null||c.isEmpty()){
            throw createException(errorMsg);
        }
    }
}
