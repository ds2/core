/**
 * 
 */
package ds2.oss.core.api;

/**
 * @author dstrauss
 *        
 */
public interface CreatedByModifiedByAware extends CreatedModifiedAware {
    String getCreatedBy();
    
    String getModifiedBy();
}
