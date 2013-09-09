/**
 * 
 */
package ds2.oss.core.options.impl;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * A boolean codec.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ValueCodecMarker(ValueType.BOOLEAN)
public class BooleanValueCodec implements ValueCodec<Boolean> {
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.options.api.ValueCodec#toString(java.lang.Object)
     */
    @Override
    public String toString(final Boolean v) {
        if (v == null) {
            return null;
        }
        return v.toString();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.options.api.ValueCodec#toValue(java.lang.String)
     */
    @Override
    public Boolean toValue(final String s) {
        if (s == null) {
            return null;
        }
        return Boolean.valueOf(s);
    }
    
}
