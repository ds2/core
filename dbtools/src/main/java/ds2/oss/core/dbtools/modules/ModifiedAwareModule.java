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

import ds2.oss.core.api.EditableModifiedAware;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author dstrauss
 */
@Embeddable
public class ModifiedAwareModule implements EditableModifiedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 4346982418358790652L;
    /**
     * The modification date.
     */
    @Column(name = "modified", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modified;

    /**
     * Inits this object.
     */
    public ModifiedAwareModule() {
        modified = LocalDateTime.now();
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.ModifiedAware#getModified()
     */
    @Override
    public LocalDateTime getModified() {
        return modified;
    }

    /**
     * Updates the modified date.
     */
    public final void touchModified() {
        modified = LocalDateTime.now();
    }

    /**
     * @param d
     */
    @Override
    public void setModified(LocalDateTime d) {
        if (d != null) {
            modified = d;
        }
    }
}
