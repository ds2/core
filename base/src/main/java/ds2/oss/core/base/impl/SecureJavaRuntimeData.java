/**
 * 
 */
package ds2.oss.core.base.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import ds2.oss.core.api.crypto.JavaRuntimeData;

/**
 * Strong java runtime data.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class SecureJavaRuntimeData implements JavaRuntimeData {
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.JavaRuntimeData#getAesMaxKeysize()
     */
    @Override
    public int getAesMaxKeysize() {
        return 256;
    }
    
}
