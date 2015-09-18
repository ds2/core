/**
 * 
 */
package ds2.oss.core.api;

/**
 * @author dstrauss
 *        
 */
public interface IHttpResponseErrorData extends IErrorData {
    /**
     * Returns the http response code to use for this type of error.
     * 
     * @return the http response code
     */
    int getHttpResponseCode();
}
