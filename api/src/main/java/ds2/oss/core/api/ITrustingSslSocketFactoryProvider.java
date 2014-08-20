/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api;

import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author dstrauss
 */
public interface ITrustingSslSocketFactoryProvider {

    SSLSocketFactory getTrustingFactory(boolean ignoreFailedServerTrusts);
}
