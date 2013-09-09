/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.enterprise.util.AnnotationLiteral;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * The value codec marker literal.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class ValueCodecMarkerLiteral extends AnnotationLiteral<ValueCodecMarker> implements ValueCodecMarker {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -4007105900167001507L;
    /**
     * The value type.
     */
    private ValueType value;
    
    /**
     * Inits the marker.
     * 
     * @param t
     *            the value type to scan for
     */
    public ValueCodecMarkerLiteral(final ValueType t) {
        value = t;
    }
    
    @Override
    public ValueType value() {
        return value;
    }
    
}
