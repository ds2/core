package ds2.oss.core.base.impl;

import ds2.oss.core.api.Validate;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by deindesign on 14.12.15.
 */
@ApplicationScoped
public class ValidateImpl implements Validate{
    @Override public boolean isEmpty(String s) {
        if(s!=null){
            String s2=s.trim();
            if(s2.length()>0){
                return false;
            }
        }
        return true;
    }

}
