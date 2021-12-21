/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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

import ds2.oss.core.api.SslServerSnooper;
import ds2.oss.core.api.annotations.SecureRandomizer;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * The impl.
 *
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
public class SslServerSnooperImpl implements SslServerSnooper {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SslServerSnooperImpl.class);
    /**
     * The path separator char to use to setup the file path.
     */
    private static final char PATHSEP = File.separatorChar;

    /**
     * Returns an empty keystore, or the platform keystore (aka trust store).
     *
     * @return the keystore
     */
    static KeyStore getCurrentKeystore() {
        KeyStore rc = null;
        final char[] ksPw = "changeit".toCharArray();
        try {
            rc = KeyStore.getInstance(KeyStore.getDefaultType());
            final String javaHome = System.getProperty("java.home");
            if (javaHome != null) {
                final File javaHomeSec = new File(javaHome + PATHSEP + "lib" + PATHSEP + "security");
                final File javaHomeKs = new File(javaHomeSec, "cacerts");
                if (javaHomeKs.canRead()) {
                    loadLocalKeystore(rc, ksPw, javaHomeKs);
                }
            } else {
                LOG.warn("No java.home set!");
            }
        } catch (final KeyStoreException e) {
            LOG.error("Error when loading a default instance of the keystore!", e);
        }
        return rc;
    }

    /**
     * Loads keystore data.
     *
     * @param rc         the keystore to fill
     * @param ksPw       the keystore password
     * @param javaHomeKs the keystore file to read data from
     */
    private static void loadLocalKeystore(final KeyStore rc, final char[] ksPw, final File javaHomeKs) {
        LOG.debug("Loading data from {}", javaHomeKs);
        try (FileInputStream fis = new FileInputStream(javaHomeKs)) {
            rc.load(fis, ksPw);
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("An algorithm error occurred!", e);
        } catch (final CertificateException e) {
            LOG.error("A certificate error occurred on reading the keystore!", e);
        } catch (final IOException e) {
            LOG.error("An IO error occurred on reading the keystore!", e);
        }
    }

    /**
     * Prints some data about the given certificate.
     *
     * @param cert the certificate
     * @return the cert data
     */
    public static String printCert(final X509Certificate cert) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Subject: ").append(cert.getSubjectDN());
        sb.append("\n").append("Issuer: ").append(cert.getIssuerDN());
        sb.append("\nSerial: ").append(cert.getSerialNumber());
        sb.append("\nFrom: ").append(cert.getNotBefore());
        sb.append("\nUntil: ").append(cert.getNotAfter());
        try {
            sb.append("\nFingerprint: ");
            for (byte b : MessageDigest.getInstance("SHA1").digest(cert.getEncoded())) {
                sb.append(String.format(":%02X", b));
            }
            sb.append("\nFingerprint SHA256: ");
            for (byte b : MessageDigest.getInstance("SHA-256").digest(cert.getEncoded())) {
                sb.append(String.format(":%02X", b));
            }
        } catch (NoSuchAlgorithmException e) {
            LOG.warn("Unknown message digest!", e);
        } catch (CertificateEncodingException e) {
            LOG.warn("Error when encoding the certificate!", e);
        }
        return sb.toString();
    }

    /**
     * Writes the received server certificates into a temporary file.
     *
     * @param aliasHeader the alias header
     * @param certs       the certs to store
     * @param pw          the password to use to encrypt the certificates
     */
    private static void writeTempKeystore(final String aliasHeader, final X509Certificate[] certs, final char[] pw) {
        if (certs == null || certs.length <= 0) {
            LOG.debug("No certs to write! Ignoring.");
            return;
        }
        try {
            final File tmpDir = new File(System.getProperty("java.io.tmpdir", "tmp"));
            final File tmpFile = File.createTempFile("ks-" + aliasHeader, ".jks", tmpDir);
            final KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, pw);
            int id = 1;
            for (X509Certificate c : certs) {
                ks.setCertificateEntry(aliasHeader + "-" + id, c);
                id++;
                final String certStr = printCert(c);
                LOG.info("Certificate to store: {}", certStr);
            }
            try (FileOutputStream fos = new FileOutputStream(tmpFile)) {
                ks.store(fos, pw);
                LOG.info("Server certificates are written to {}", tmpFile.getAbsolutePath());
            } catch (final NoSuchAlgorithmException e) {
                LOG.warn("Algorithm error occurred!", e);
            } catch (final CertificateException e) {
                LOG.warn("A certificate error occurred!", e);
            }
        } catch (final KeyStoreException e) {
            LOG.warn("A keystore error occurred!", e);
        } catch (final IOException e) {
            LOG.warn("An IO error occurred", e);
        } catch (final NoSuchAlgorithmException e1) {
            LOG.warn("ALgorithm error occurred!", e1);
        } catch (final CertificateException e1) {
            LOG.warn("Cert error occurred!", e1);
        }

    }

    /**
     * Secure Randomizer.
     */
    @SecureRandomizer
    private SecureRandom secRandom;

    @Override
    public X509Certificate[] getServerCertificates(final String hostname, final int port) {
        final KeyStore platformKeystore = getCurrentKeystore();
        LOG.debug("Using ca store {}", platformKeystore);
        X509Certificate[] rc = null;
        try {
            final SSLContext sslCtx = SSLContext.getInstance("TLS");
            final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(platformKeystore);
            final X509TrustManager defTm = (X509TrustManager) tmf.getTrustManagers()[0];
            final TrustManagerWrapper tmw = new TrustManagerWrapper(defTm);
            sslCtx.init(null, new TrustManager[]{tmw}, secRandom);
            final SSLSocketFactory sslsf = sslCtx.getSocketFactory();
            // connect
            try (SSLSocket socket = (SSLSocket) sslsf.createSocket(hostname, port)) {
                socket.setSoTimeout(15000);
                socket.startHandshake();
            } finally {
                rc = tmw.getServerCerts();
                writeTempKeystore(hostname, rc, "changeit".toCharArray());
            }
        } catch (final NoSuchAlgorithmException e) {
            LOG.error("Unknown algorithm found!", e);
        } catch (final KeyStoreException e) {
            LOG.error("Error in keystore handling!", e);
        } catch (final KeyManagementException e) {
            LOG.error("Key Management error occurred!", e);
        } catch (final UnknownHostException e) {
            LOG.error("Strange hostname!", e);
        } catch (final IOException e) {
            LOG.error("Error when reading the SSL sockets.", e);
        }
        LOG.debug("Certificates so far: {}", (Object[]) rc);
        return rc;
    }
}
