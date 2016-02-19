/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.dbtools;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.EntryStates;
import ds2.oss.core.api.StateAware;
import ds2.oss.core.dbtools.modules.EntryStatesAwareModule;

/**
 * An entity that maps the states to the enum {@link EntryStates}.
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
