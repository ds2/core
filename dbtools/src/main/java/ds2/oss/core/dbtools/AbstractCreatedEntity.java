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

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import ds2.oss.core.api.CreatedAware;
import ds2.oss.core.dbtools.modules.CreatedAwareModule;

/**
 * @author dstrauss
 *         
 */
@MappedSuperclass
public abstract class AbstractCreatedEntity implements CreatedAware {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8489399779935854162L;
    /**
     * The ca module.
     */
    @Embedded
    private CreatedAwareModule cam = new CreatedAwareModule();
    
    /**
     * Inits this object.
     */
    public AbstractCreatedEntity() {
        if (cam == null) {
            cam = new CreatedAwareModule();
        }
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedAware#getCreated()
     */
    @Override
    public Date getCreated() {
        if (cam == null) {
            cam = new CreatedAwareModule();
        }
        return cam.getCreated();
    }
    
    /**
     * Sets the creation date.
     * 
     * @param date
     *            the creation date.
     */
    public void setCreated(Date date) {
        if (cam == null) {
            cam = new CreatedAwareModule();
        }
        cam.setCreated(date);
    }
    
}
