package ds2.oss.core.api.options;

import ds2.oss.core.api.CreatedModifiedAware;
import ds2.oss.core.api.Persistable;

/**
 * A single option value.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the persistence type
 * @param <V>
 *            the value type of this value
 */
public interface OptionValue<E, V> extends Persistable<E>, CreatedModifiedAware, TimeRanged {
    /**
     * Returns the name or identifier of the approver of this option value.
     * 
     * @return the identifier of the approver. May return null if not yet approved
     */
    String getApproverName();
    
    /**
     * Returns the name or identifier of the author of this option value.
     * 
     * @return the identifier
     */
    String getAuthorName();
    
    /**
     * Returns the referenced option id.
     * 
     * @return the referenced option id
     */
    E getOptionReference();
    
    /**
     * Returns the current stage of this value.
     * 
     * @return the stage of this value
     */
    OptionValueStage getStage();
    
    /**
     * Returns the value of this option value object.
     * 
     * @return the value
     */
    V getValue();
}
