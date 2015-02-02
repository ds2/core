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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ds2.oss.core.api.CreatedModifiedAware;

/**
 * Embeddable module for the createdModified system.
 *
 * @author dstrauss
 * @version 0.1
 */
@Embeddable
public class CreatedModifiedAwareModule implements CreatedModifiedAware {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 305082117441818515L;
    /**
     * The creation date.
     */
    @Column(name = "created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    /**
     * The modification date.
     */
    @Column(name = "modified", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    /**
     * Inits the module.
     */
    public CreatedModifiedAwareModule() {
        created = new Date();
        modified = new Date();
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public Date getModified() {
        return modified;
    }

    /**
     * Sets the creation date.
     *
     * @param c
     *            the created to set
     */
    public void setCreated(final Date c) {
        created = c;
    }

    /**
     * Sets the modification date.
     *
     * @param m
     *            the modified to set
     */
    public void setModified(final Date m) {
        modified = m;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreatedModifiedAwareModule (created=");
        builder.append(created);
        builder.append(", modified=");
        builder.append(modified);
        builder.append(")");
        return builder.toString();
    }

    /**
     * Updates the modified date.
     */
    public final void touchModified() {
        modified = new Date();
    }

}