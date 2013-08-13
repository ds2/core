package ds2.oss.core.api.environment;

/**
 * A runtime cluster.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface Cluster {
    /**
     * Returns the name of the cluster.
     * 
     * @return the name of the cluster
     */
    char getClusterName();
}
