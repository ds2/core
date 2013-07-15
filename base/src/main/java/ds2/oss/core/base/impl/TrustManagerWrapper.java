/**
 * 
 */
package ds2.oss.core.base.impl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * A trust manager that wraps requests.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class TrustManagerWrapper implements X509TrustManager {
    /**
     * The original trust manager to use.
     */
    private final X509TrustManager origTm;
    /**
     * The server certificates.
     */
    private X509Certificate[] serverCerts;
    
    /**
     * Inits this trust manager with a given original tm.
     * 
     * @param tm
     *            the original trust manager to use
     */
    public TrustManagerWrapper(final X509TrustManager tm) {
        origTm = tm;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.
     * X509Certificate[], java.lang.String)
     */
    @Override
    public void checkClientTrusted(final X509Certificate[] chain,
        final String authType) throws CertificateException {
        origTm.checkClientTrusted(chain, authType);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.
     * X509Certificate[], java.lang.String)
     */
    @Override
    public void checkServerTrusted(final X509Certificate[] chain,
        final String authType) throws CertificateException {
        serverCerts = chain;
        origTm.checkServerTrusted(chain, authType);
    }
    
    /*
     * (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return origTm.getAcceptedIssuers();
    }
    
    /**
     * Returns the server certificates sent by the server.
     * 
     * @return the serverCerts
     */
    public X509Certificate[] getServerCerts() {
        return serverCerts;
    }
    
}
