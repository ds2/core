/**
 * 
 */
package ds2.oss.core.base.impl.test;

import java.security.cert.X509Certificate;

import ds2.oss.core.api.SslServerSnooper;

/**
 * A small test client.
 * 
 * @author dstrauss
 * @version 0.2
 */
public final class SslServerSnooperTestClient {
    
    /**
     * Dummy constructor.
     */
    private SslServerSnooperTestClient() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Performs a query to a given server.
     * 
     * @param args
     *            the server hostname
     */
    public static void main(final String[] args) {
        WeldWrapper.onSuiteStart();
        final SslServerSnooper to =
            WeldWrapper.getInstance(SslServerSnooper.class);
        String hostname = args.length > 0 ? args[0] : "localhost";
        final X509Certificate[] certs = to.getServerCertificates(hostname, 443);
        System.out.println(certs);
        WeldWrapper.afterSuite();
    }
}
