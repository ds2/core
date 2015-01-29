/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IErrorData;

/**
 * @author dstrauss
 *
 */
public class ElasticSearchException extends CoreException {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -4038016233235930815L;
    
    /**
     * @param d
     */
    public ElasticSearchException(IErrorData d) {
        super(d);
    }
    
    /**
     * @param d
     * @param t
     */
    public ElasticSearchException(IErrorData d, Throwable t) {
        super(d, t);
    }
    
}
