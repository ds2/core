/**
 * 
 */
package ds2.oss.core.elasticsearch.impl.literals;

import javax.enterprise.util.AnnotationLiteral;

import ds2.oss.core.elasticsearch.api.annotations.TransportTypes;
import ds2.oss.core.elasticsearch.api.annotations.UsingTransport;

/**
 * @author dstrauss
 *
 */
public class TransportLiteral extends AnnotationLiteral<UsingTransport> implements UsingTransport {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 3244594469497508259L;
    private TransportTypes value;
    
    /**
     * @param t
     *            the transport type
     * 
     */
    public TransportLiteral(TransportTypes t) {
        value = t;
    }
    
    @Override
    public TransportTypes value() {
        return value;
    }
}
