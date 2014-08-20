/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.base.impl;

import ds2.oss.core.api.ITrustingSslSocketFactoryProvider;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dstrauss
 */
public class TrustingSslSocketFactoryProvider implements ITrustingSslSocketFactoryProvider {

    private static final Logger LOG = LoggerFactory.getLogger(TrustingSslSocketFactoryProvider.class);

    @Override
    public SSLSocketFactory getTrustingFactory(boolean ignoreSslErrors) {
        SSLSocketFactory rc = null;
        final KeyStore platformKeystore = SslServerSnooperImpl.getCurrentKeystore();
        LOG.debug("Using ca store {}", platformKeystore);
        try {
            final SSLContext sslCtx = SSLContext.getInstance("TLS");
            final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(platformKeystore);
            final X509TrustManager defTm = (X509TrustManager) tmf.getTrustManagers()[0];
            final TrustManagerWrapper tmw = new TrustManagerWrapper(defTm);
            tmw.setIgnoreServerTrusted(ignoreSslErrors);
            sslCtx.init(null, new TrustManager[]{tmw}, null);
            rc = sslCtx.getSocketFactory();
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException ex) {
            LOG.error("Error when setting up the socket factory!", ex);
        } finally {

        }
        return rc;
    }

}
