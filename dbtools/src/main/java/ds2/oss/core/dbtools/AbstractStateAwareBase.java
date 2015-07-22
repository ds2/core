/**
 * 
 */
package ds2.oss.core.dbtools;

import javax.persistence.MappedSuperclass;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.StateAware;

/**
 * @author dstrauss
 * @param <E>
 *            the entry state type. Can and should be an entity bean.
 *            
 */
@MappedSuperclass
public abstract class AbstractStateAwareBase<E extends EntryState> implements StateAware {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -4751173674735427623L;
    /**
     * The state column.
     */
    private E entryState;
    
    /**
     * Sets the entryState.
     * 
     * @param entryState
     *            the entryState to set
     */
    public void setEntryState(E entryState) {
        this.entryState = entryState;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.StateAware#getEntryState()
     */
    @Override
    public EntryState getEntryState() {
        return entryState;
    }
    
}
