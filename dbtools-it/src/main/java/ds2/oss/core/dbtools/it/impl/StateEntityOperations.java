/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.dbtools.it.impl;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.dbtools.AbstractPersistenceSupportImpl;
import ds2.oss.core.dbtools.it.entities.StateEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author dstrauss
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class StateEntityOperations extends AbstractPersistenceSupportImpl<StateEntity, Long> {
    @PersistenceContext(unitName = "octest")
    private EntityManager em;

    public EntryState getById(long l) {
        return getById(Long.valueOf(l));
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.dbtools.DefaultEntityOperations#getEntityClass()
     */
    @Override
    protected Class<StateEntity> getEntityClass() {
        return StateEntity.class;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public StateEntity getById(Long e) {
        return getSecureFindById(em, e);
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(StateEntity t) {
        create(em, t);
    }

    @Override
    public void deleteById(Long id) {
        em.remove(em.find(StateEntity.class, id));
    }
}
