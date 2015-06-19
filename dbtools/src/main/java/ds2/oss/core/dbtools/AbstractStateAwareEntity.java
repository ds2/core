package ds2.oss.core.dbtools;

import ds2.oss.core.api.EntryStates;
import ds2.oss.core.api.StateAware;
import ds2.oss.core.dbtools.modules.StateAwareModule;

import javax.persistence.Embedded;

/**
 * Created by dstrauss on 18.06.15.
 */
public abstract class AbstractStateAwareEntity implements StateAware {
    @Embedded
    private StateAwareModule stateAwareModule=new StateAwareModule();

    @Override
    public EntryStates getEntryState() {
        if(stateAwareModule==null){
            stateAwareModule=new StateAwareModule();
        }
        return stateAwareModule.getEntryState();
    }
    public void setEntryState(EntryStates state){
        if(stateAwareModule==null){
            stateAwareModule=new StateAwareModule();
        }
        stateAwareModule.setEntryState(state);
    }
}
