/*
 * Copyright 2012-2013 Dirk Strauss
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
package ds2.oss.core.base.impl;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.environment.ServerIdentifierDto;
import ds2.oss.core.api.environment.ServerIdentifierProvider;

/**
 * A server snooper.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class ServerIdentifierProviderImpl implements ServerIdentifierProvider {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ServerIdentifierProviderImpl.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final ServerIdentifier getCurrentServerDetails() {
        final ServerIdentifierDto rc = new ServerIdentifierDto();
        rc.setDomain(null);
        try {
            final InetAddress lh = InetAddress.getLocalHost();
            rc.setHostName(lh.getHostName());
            final Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                final NetworkInterface ni = netInterfaces.nextElement();
                LOG.debug(ni.getName());
                final Enumeration<InetAddress> interfaceInetAddresses = ni.getInetAddresses();
                while (interfaceInetAddresses.hasMoreElements()) {
                    final InetAddress ip = interfaceInetAddresses.nextElement();
                    if (ip instanceof Inet6Address) {
                        // we don't yet support ipv6
                        continue;
                    }
                    LOG.info("IA for {} is {}", ni.getName(), ip);
                    if (!ip.isLoopbackAddress() && (ip.getHostAddress().indexOf(":") == -1)) {
                        LOG.debug("Interface {} seems to be InternetInterface. I'll take it...", ni.getName());
                        rc.addAddress(ip);
                        break;
                    }
                }
            }
        } catch (final UnknownHostException e) {
            LOG.warn("Error when analyzing the local host!", e);
        } catch (final SocketException e) {
            LOG.warn("Error when analyzing the network!", e);
        }
        return rc;
    }
}
