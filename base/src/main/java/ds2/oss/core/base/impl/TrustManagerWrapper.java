/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
     * Whether to crash on invalid certs, or to ignore them.
     */
    private boolean ignoreServerTrusted;
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
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert. X509Certificate[],
     * java.lang.String)
     */
    @Override
    public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
        origTm.checkClientTrusted(chain, authType);
    }

    /*
     * (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert. X509Certificate[],
     * java.lang.String)
     */
    @Override
    public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
        serverCerts = chain;
        try {
            origTm.checkServerTrusted(chain, authType);
        } catch (final CertificateException e) {
            if (!ignoreServerTrusted) {
                throw e;
            }
        }
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

    /**
     * Returns if we can ignore any server trust errors.
     *
     * @return TRUE to ignore any server trust errors, otherwise FALSE
     */
    public boolean isIgnoreServerTrusted() {
        return ignoreServerTrusted;
    }

    /**
     * Sets the server trust flag.
     *
     * @param ignoreServerTrusted
     *            TRUE to ignore any server trust errors, otherwise and by default FALSE to crash on
     *            server trust errors
     */
    public void setIgnoreServerTrusted(final boolean ignoreServerTrusted) {
        this.ignoreServerTrusted = ignoreServerTrusted;
    }

}
