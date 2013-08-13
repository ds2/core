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
