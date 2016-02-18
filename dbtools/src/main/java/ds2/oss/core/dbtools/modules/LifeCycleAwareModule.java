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

import ds2.oss.core.api.LifeCycleAware;

/**
 * A life cycle aware db module.
 *
 * @author dstrauss
 * @version 0.3
 *
 */
@Embeddable
public class LifeCycleAwareModule implements LifeCycleAware {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6897754896689099253L;
    /**
     * Valid from.
     */
    @Column(name = "valid_from", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    /**
     * Valid to.
     */
    @Column(name = "valid_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;

    /**
     * Inits the module.
     */
    public LifeCycleAwareModule() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.LifeCycleAware#getValidFrom()
     */
    @Override
    public Date getValidFrom() {
        return validFrom;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.LifeCycleAware#getValidTo()
     */
    @Override
    public Date getValidTo() {
        return validTo;
    }

    public void setValidFrom(final Date validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(final Date validTo) {
        this.validTo = validTo;
    }

}
