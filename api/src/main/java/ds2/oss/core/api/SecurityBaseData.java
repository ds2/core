package ds2.oss.core.api;

/**
 * Contract for sec base data.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface SecurityBaseData {
    /**
     * Returns the salt to use.
     * 
     * @return the salt value
     */
    byte[] getSalt();
    
    /**
     * Returns the minimum number of iterations to use to create a hash value.
     * 
     * @return the iteration count
     */
    int getMinIteration();
}
