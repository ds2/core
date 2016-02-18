/**
 * 
 */
package ds2.oss.core.api;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dstrauss
 *         
 */
public interface ModifiedAware extends Serializable {
    /**
     * Returns the modification date.
     *
     * @return the modification date
     */
    Date getModified();
}
