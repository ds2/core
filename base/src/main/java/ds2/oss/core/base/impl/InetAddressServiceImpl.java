/**
 * 
 */
package ds2.oss.core.base.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.InetAddressService;

/**
 * @author dstrauss
 *         
 */
@ApplicationScoped
public class InetAddressServiceImpl implements InetAddressService {
    private static final Logger LOG = LoggerFactory.getLogger(InetAddressServiceImpl.class);
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.InetAddressService#getByName(java.lang.String)
     */
    @Override
    public InetAddress getByName(String name) {
        try {
            return InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            LOG.debug("Error when getting the ip address for " + name, e);
        }
        return null;
    }
    
}
