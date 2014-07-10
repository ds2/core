/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.base.impl;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import ds2.oss.core.api.EntryStates;
import ds2.oss.core.api.StateAware;

/**
 * Module for a state entry.
 * 
 * @author dstrauss
 * @version 0.1
 */
@Embeddable
public class StateAwareModule implements StateAware {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8358311170080845039L;
    /**
     * The converter.
     */
    @Transient
    private final NumericalEnumConverter<EntryStates> c;
    /**
     * The state id.
     */
    @Column(name = "state_id", nullable = false, updatable = true)
    private int stateId;
    
    /**
     * Inits the module.
     */
    public StateAwareModule() {
        c = new NumericalEnumConverter<>(EntryStates.class);
        stateId = EntryStates.PREPARED.getNumericalValue();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.StateAware#getState()
     */
    @Override
    public EntryStates getEntryState() {
        return c.getEnumByReflection(stateId, "getById", EntryStates.class);
    }
    
    /**
     * Sets the entry state.
     * 
     * @param s
     *            the state to set
     */
    public void setEntryState(final EntryStates s) {
        if (s == null) {
            throw new IllegalArgumentException("Bad state value!");
        }
        stateId = c.getValue(s);
    }
    
}
