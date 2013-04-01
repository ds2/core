/**
 * 
 */
package ds2.oss.core.api;

import java.io.Serializable;

/**
 * Contract for a state aware dto.
 * 
 * @author dstrauss
 * @version 0.1
 */
public interface StateAware extends Serializable {
    /**
     * Returns the state of the entry.
     * 
     * @return the state. May return null if not set.
     */
    EntryStates getState();
}
