/**
 * 
 */
package ds2.oss.core.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Contract for an entity that has a creation date.
 * 
 * @author dstrauss
 *         
 */
public interface CreatedAware extends Serializable {
    /**
     * Returns the creation date. It is expected that this method must not return null.
     * 
     * @return the creation date.
     */
    Date getCreated();
}
