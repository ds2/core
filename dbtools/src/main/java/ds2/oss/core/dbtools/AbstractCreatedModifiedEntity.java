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

import ds2.oss.core.api.CreatedModifiedAware;
import ds2.oss.core.api.EditableCreatedModifiedAware;
import ds2.oss.core.dbtools.modules.CreatedAwareModule;
import ds2.oss.core.dbtools.modules.ModifiedAwareModule;

/**
 * Created by dstrauss on 18.06.15.
 */
@MappedSuperclass
public abstract class AbstractCreatedModifiedEntity extends AbstractCreatedEntity implements EditableCreatedModifiedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8846962750701956032L;
    /**
     * The embeddable to handle the columns.
     */
    @Embedded
    private CreatedAwareModule cam = new CreatedAwareModule();
    @Embedded
    private ModifiedAwareModule mam = new ModifiedAwareModule();
    
    @Override
    public Date getCreated() {
        return getCam().getCreated();
    }
    
    /**
     * Sets the creation date.
     * 
     * @param date
     *            the creation date.
     */
    @Override
    public void setCreated(Date date) {
        getCam().setCreated(date);
    }
    
    @Override
    public Date getModified() {
        return getMam().getModified();
    }
    
    /**
     * Sets the modified date. Usually you want to use {@link #touchModified()} instead.
     * 
     * @param d
     *            the modification date
     */
    @Override
    public void setModified(Date d) {
        getMam().setModified(d);
    }
    
    private ModifiedAwareModule getMam() {
        if (mam == null) {
            mam = new ModifiedAwareModule();
        }
        return mam;
    }
    
    private CreatedAwareModule getCam() {
        if (cam == null) {
            cam = new CreatedAwareModule();
        }
        return cam;
    }
    
    public void touchModified() {
        getMam().touchModified();
    }
}
