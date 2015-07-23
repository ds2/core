/**
 * 
 */
package ds2.oss.core.api;

/**
 * @author dstrauss
 *         
 */
public interface CreatedByModifiedByAware extends CreatedModifiedAware {
    /**
     * Returns the username who created this entry.
     * 
     * @return the username
     */
    String getCreatedBy();
    
    /**
     * Returns the modifier username.
     * 
     * @return the username who modified this entry.
     */
    String getModifiedBy();
}
