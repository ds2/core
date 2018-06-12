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

import ds2.oss.core.api.EditableStateAware;
import ds2.oss.core.api.EntryState;

import javax.persistence.MappedSuperclass;

/**
 * An entity that maps the states to the dto {@link EntryState}.
 *
 * @param <E> the entry state type. Can and should be an entity bean.
 * @author dstrauss
 */
@MappedSuperclass
public abstract class AbstractStateAwareBase<E extends EntryState> implements EditableStateAware<E> {

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
     * @param entryState the entryState to set
     */
    @Override
    public void setEntryState(E entryState) {
        this.entryState = entryState;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.StateAware#getEntryState()
     */
    @Override
    public E getEntryState() {
        return entryState;
    }

}
