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
package ds2.oss.core.base.impl.test;

import java.security.cert.X509Certificate;

import ds2.oss.core.api.SslServerSnooper;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;

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
        AbstractInjectionEnvironment.onSuiteStart();
        final SslServerSnooper to =
            AbstractInjectionEnvironment.getInstance(SslServerSnooper.class);
        final String hostname = args.length > 0 ? args[0] : "jiri.jamba.net";
        final X509Certificate[] certs = to.getServerCertificates(hostname, 443);
        System.out.println(certs);
        AbstractInjectionEnvironment.onSuiteEnd();
    }
}
