package ds2.oss.core.api.options;

import java.io.Serializable;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import ds2.oss.core.api.environment.ServerIdentifier;

/**
 * The context of an option value.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionValueContext extends Serializable {
    /**
     * Returns the cluster of the option value.
     * 
     * @return the cluster
     */
    Cluster getCluster();
    
    /**
     * Returns the configuration this value is meant for.
     * 
     * @return the runtime configuration
     */
    RuntimeConfiguration getConfiguration();
    
    /**
     * Returns the server this value is meant for.
     * 
     * @return the server. Null indicates all servers, no specific one.
     */
    ServerIdentifier getServer();
}
