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
import ds2.oss.core.dbtools.it.MyEntityService;
import ds2.oss.core.dbtools.it.entities.MyEntity;
import ds2.oss.core.dbtools.it.entities.StateEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * Created by dstrauss on 19.06.15.
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class MyEntityServiceBean extends AbstractPersistenceSupportImpl<MyEntity, Long> implements MyEntityService {
    private static final Logger LOG = LoggerFactory.getLogger(MyEntityServiceBean.class);
    @PersistenceContext(unitName = "octest")
    private EntityManager em;
    @Inject
    private StateEntityOperations ses;

    @Override
    public MyEntity create(String name, EntryState state) {
        MyEntity m = new MyEntity();
        m.setDate(new Date());
        m.setName(name);
        StateEntity thisState = ses.getById(Long.valueOf(state.getNumericalValue()));
        m.setEntryState(thisState);
        em.persist(m);
        return m;
    }

    @Override
    public MyEntity getEntityById(long id) {
        return getSecureFindById(em, Long.valueOf(id));
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public MyEntity getById(Long e) {
        return getSecureFindById(em, e);
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(MyEntity t) {
        create(em, t);
    }

    @Override
    public void deleteById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<MyEntity> cq = cb.createCriteriaDelete(MyEntity.class);
        Root<MyEntity> root = cq.from(MyEntity.class);
        cq.where(cb.equal(root.get("id"), id));
        em.createQuery(cq).executeUpdate();
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.dbtools.AbstractPersistenceSupportImpl#getEntityClass()
     */
    @Override
    protected Class<MyEntity> getEntityClass() {
        return MyEntity.class;
    }
}
