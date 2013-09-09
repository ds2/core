/**
 * 
 */
package ds2.oss.core.options.impl;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * A string value codec.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ValueCodecMarker(ValueType.STRING)
public class StringValueCodec implements ValueCodec<String> {
    
    @Override
    public String toString(final String v) {
        return v;
    }
    
    @Override
    public String toValue(final String s) {
        return s;
    }
    
}
