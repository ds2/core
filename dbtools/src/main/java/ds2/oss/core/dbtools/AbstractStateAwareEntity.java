package ds2.oss.core.dbtools;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.EntryStates;
import ds2.oss.core.api.StateAware;
import ds2.oss.core.dbtools.modules.EntryStatesAwareModule;

/**
 * Created by dstrauss on 18.06.15.
 */
@MappedSuperclass
public abstract class AbstractStateAwareEntity implements StateAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 4368250604091132964L;
    @Embedded
    private EntryStatesAwareModule stateAwareModule = new EntryStatesAwareModule();
    
    @Override
    public EntryState getEntryState() {
        if (stateAwareModule == null) {
            stateAwareModule = new EntryStatesAwareModule();
        }
        return stateAwareModule.getEntryState();
    }
    
    public void setEntryState(EntryStates state) {
        if (stateAwareModule == null) {
            stateAwareModule = new EntryStatesAwareModule();
        }
        stateAwareModule.setEntryState(state);
    }
}
