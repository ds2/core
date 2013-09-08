/*
 * Copyright 2012-2013 Dirk Strauss
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
/**
 * 
 */
package ds2.oss.core.options.it;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.options.api.OptionsPersistenceSupport;
import ds2.oss.core.options.impl.AbstractOptionsPersistenceSupportBean;

/**
 * @author dstrauss
 * 
 */
@Stateless(name = "persistenceBean")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@OptionsPersistenceSupport
public class PersistenceSupportBean extends AbstractOptionsPersistenceSupportBean {
    /**
     * The entity manager.
     */
    @PersistenceContext(unitName = "corePU")
    private EntityManager em;
    
    @Override
    public void persist(final Option<Long, Object> t) {
        performPersist(em, t);
    }
    
    @Override
    public Option<Long, Object> getById(final Long e) {
        return null;
    }
    
}
