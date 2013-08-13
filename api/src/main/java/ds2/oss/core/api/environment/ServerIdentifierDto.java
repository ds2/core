package ds2.oss.core.api.environment;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A dto for any known server details.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class ServerIdentifierDto implements ServerIdentifier {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The inet addresses of the server.
     */
    private List<InetAddress> addresses;
    /**
     * The ip address.
     */
    private String ipAddress;
    /**
     * The hostname of the server.
     */
    private String hostName;
    /**
     * A possible domain name of the server.
     */
    private String domain;
    
    /**
     * Inits the dto.
     */
    public ServerIdentifierDto() {
        addresses = new ArrayList<>();
    }
    
    @Override
    public final List<InetAddress> getAllKnownAddresses() {
        return addresses;
    }
    
    @Override
    public final String getDomain() {
        return domain;
    }
    
    @Override
    public final String getHostName() {
        return hostName;
    }
    
    /**
     * Sets the ip address.
     * 
     * @param ip
     *            ip address
     */
    public final void setIpAddress(final String ip) {
        ipAddress = ip;
    }
    
    /**
     * Sets the host name.
     * 
     * @param host
     *            the host name
     */
    public void setHostName(final String host) {
        hostName = host;
    }
    
    /**
     * Sets the domain name.
     * 
     * @param dom
     *            the domain name
     */
    public void setDomain(final String dom) {
        domain = dom;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.foundation.environment.api.ServerIdentifier#getIpAddress()
     */
    @Override
    public String getIpAddress() {
        return ipAddress;
    }
    
    /**
     * Adds all addresses given to the currently known addresses.
     * 
     * @param addresses2
     *            the addresses to add
     */
    public void addAddresses(final Collection<InetAddress> addresses2) {
        addresses.addAll(addresses2);
    }
    
    /**
     * Adds a single new address.
     * 
     * @param ia2
     *            the address to add
     */
    public void addAddress(final InetAddress ia2) {
        addresses.add(ia2);
    }
    
}
