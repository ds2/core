/**
 * 
 */
package ds2.oss.core.options.impl;

import java.net.MalformedURLException;
import java.net.URL;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * A url value codec.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ValueCodecMarker(ValueType.URL)
public class UrlValueCodec implements ValueCodec<URL> {
    
    @Override
    public String toString(final URL v) {
        if (v == null) {
            return null;
        }
        return v.toString();
    }
    
    @Override
    public URL toValue(final String s) {
        URL rc = null;
        try {
            rc = new URL(s);
        } catch (final MalformedURLException e) {
        }
        return rc;
    }
    
}
