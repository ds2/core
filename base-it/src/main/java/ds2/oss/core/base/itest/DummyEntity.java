/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 *
 */
package ds2.oss.core.base.itest;

import ds2.oss.core.api.EditableCreatedModifiedAware;
import ds2.oss.core.api.EditableStateAware;
import ds2.oss.core.api.EntryStates;
import ds2.oss.core.api.Persistable;
import ds2.oss.core.dbtools.modules.CreatedModifiedAwareModule;
import ds2.oss.core.dbtools.modules.EntryStatesAwareModule;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Dummy entity.
 *
 * @author dstrauss
 * @version 0.2
 */
@Entity
@Table(name = "core_dummy")
public class DummyEntity implements Persistable<Long>, EditableStateAware<EntryStates>, EditableCreatedModifiedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -2333206982194087887L;
    /**
     * An id.
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * The state.
     */
    @Embedded
    private final EntryStatesAwareModule state;
    /**
     * The times.
     */
    @Embedded
    private final CreatedModifiedAwareModule times;

    /**
     * Inits the entity.
     */
    public DummyEntity() {
        state = new EntryStatesAwareModule();
        times = new CreatedModifiedAwareModule();
    }

    @Override
    public LocalDateTime getCreated() {
        return times.getCreated();
    }

    @Override
    public EntryStates getEntryState() {
        return state.getEntryState();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public LocalDateTime getModified() {
        return times.getModified();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DummyEntity (id=");
        builder.append(id);
        builder.append(", state=");
        builder.append(state);
        builder.append(", times=");
        builder.append(times);
        builder.append(")");
        return builder.toString();
    }

    @Override
    public void setEntryState(EntryStates newState) {
        state.setEntryState(newState);
    }

    @Override
    public void setCreated(LocalDateTime d) {
        times.setCreated(d);
    }

    @Override
    public void setModified(LocalDateTime d) {
        times.setModified(d);
    }
}
