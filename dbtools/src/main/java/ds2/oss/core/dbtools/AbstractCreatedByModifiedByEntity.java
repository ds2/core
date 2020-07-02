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
package ds2.oss.core.dbtools;

import ds2.oss.core.api.EditableCreatedByModifiedByAware;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author dstrauss
 */
@MappedSuperclass
public abstract class AbstractCreatedByModifiedByEntity extends AbstractCreatedModifiedEntity
        implements
        EditableCreatedByModifiedByAware {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6836168305533415114L;
    @Column(name = "createdby", nullable = false, updatable = false)
    private String createdBy;
    @Column(name = "modifiedby")
    private String modifiedBy;

    /**
     * Sets the createdBy.
     *
     * @param createdBy the createdBy to set
     */
    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the modifiedBy.
     *
     * @param modifiedBy the modifiedBy to set
     */
    @Override
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedByModifiedByAware#getCreatedBy()
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedByModifiedByAware#getModifiedBy()
     */
    @Override
    public String getModifiedBy() {
        return modifiedBy;
    }

}
