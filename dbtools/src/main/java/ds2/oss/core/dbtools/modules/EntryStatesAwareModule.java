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
package ds2.oss.core.dbtools.modules;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.EntryStates;
import ds2.oss.core.api.StateAware;
import ds2.oss.core.dbtools.converters.EntryStatesConverter;

/**
 * Module for a state entry using the default enum {@link EntryStates}. Usually,
 * this should not be used in production environments where a state can have
 * more than the given default values from our enum. But it is a good starting
 * point.
 *
 * @author dstrauss
 * @version 0.1
 */
@Embeddable
public class EntryStatesAwareModule implements StateAware {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8358311170080845039L;
    /**
     * The state id.
     */
    @Column(name = "state_id", nullable = false, updatable = true)
    @Convert(converter = EntryStatesConverter.class)
    private EntryStates entryState = EntryStates.PREPARED;

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.StateAware#getState()
     */
    @Override
    public EntryState getEntryState() {
        return entryState;
    }

    /**
     * Sets the entry state.
     *
     * @param s the state to set
     */
    public void setEntryState(final EntryStates s) {
        if (s == null) {
            throw new IllegalArgumentException("Bad state value!");
        }
        entryState = s;
    }

}
