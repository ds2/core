/**
 * 
 */
package ds2.oss.core.base.impl;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.crypto.JavaRuntimeData;

/**
 * The default java runtime data.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class DefaultJavaRuntimeData implements JavaRuntimeData {
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.JavaRuntimeData#getAesMaxKeysize()
     */
    @Override
    public int getAesMaxKeysize() {
        return 128;
    }
    
}
