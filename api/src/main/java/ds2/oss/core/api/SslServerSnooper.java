/**
 * 
 */
package ds2.oss.core.api;

import java.security.cert.X509Certificate;

/**
 * A service to check the certificates of a given server.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface SslServerSnooper {
    /**
     * Gets all known certificates sent by the given SSL server.
     * 
     * @param hostname
     *            the hostname
     * @param port
     *            the port
     * @return the found certificates
     */
    X509Certificate[] getServerCertificates(String hostname, int port);
}
