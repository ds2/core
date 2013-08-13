package ds2.oss.core.api.environment;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.List;

/**
 * Interface contract to identify a single computer in a network.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface ServerIdentifier extends Serializable {
    /**
     * Returns all known ip addresses of this server.
     * 
     * @return a list of all known ip addresses
     */
    List<InetAddress> getAllKnownAddresses();
    
    /**
     * Returns the possible domain this computer belongs to.
     * 
     * @return the domain name, if available. Otherwise null.
     */
    String getDomain();
    
    /**
     * Returns the hostname of this server.
     * 
     * @return the name of this server
     */
    String getHostName();
    
    /**
     * Returns the possible remote address of this server. Usually, this will return the internal ip
     * address to be used by configuration clients or similar.
     * 
     * @return the possible remote address.
     */
    String getIpAddress();
}
