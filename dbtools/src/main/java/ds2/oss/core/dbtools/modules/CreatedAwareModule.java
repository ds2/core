/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.dbtools.modules;

import ds2.oss.core.api.EditableCreatedAware;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

/**
 * @author dstrauss
 */
@Embeddable
public class CreatedAwareModule implements EditableCreatedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -6249059771176243495L;
    /**
     * The creation date.
     */
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    /**
     * Inits this object.
     */
    public CreatedAwareModule() {
        created = LocalDateTime.now();
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedAware#getCreated()
     */
    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public void setCreated(LocalDateTime d) {
        created = d;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        if (created != null) {
            builder.append("created=");
            builder.append(created);
        }
        builder.append(")");
        return builder.toString();
    }

}
