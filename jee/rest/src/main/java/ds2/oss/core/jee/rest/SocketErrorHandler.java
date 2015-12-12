/**
 * 
 */
package ds2.oss.core.jee.rest;

import ds2.oss.core.api.JaxRsClientException;

/**
 * @author dstrauss
 *         
 */
public interface SocketErrorHandler<E extends JaxRsClientException> {
    void handleException(Exception e) throws E;
}
