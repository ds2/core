/**
 * 
 */
package ds2.oss.core.api;

/**
 * @author dstrauss
 *
 */
public class JsonCodecException extends CodecException {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 7047560345247655308L;
    
    /**
     * @param d
     */
    public JsonCodecException(IErrorData d) {
        super(d);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param d
     * @param t
     */
    public JsonCodecException(IErrorData d, Throwable t) {
        super(d, t);
        // TODO Auto-generated constructor stub
    }
    
}
