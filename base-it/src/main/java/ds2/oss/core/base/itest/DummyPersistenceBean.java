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
/**
 *
 */
package ds2.oss.core.base.itest;

import ds2.oss.core.api.persistence.InvalidEntityException;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 * The persistence bean.
 *
 * @author dstrauss
 * @version 0.2
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DummyPersistenceBean implements DummyPersistence {
    /**
     * The entity manager.
     */
    @PersistenceContext(unitName = "corePU")
    private EntityManager em;

    /**
     * INits the bean.
     */
    public DummyPersistenceBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public DummyEntity getById(final Long e) {
        return em.find(DummyEntity.class, e);
    }

    @Override
    public void persist(DummyEntity t) throws InvalidEntityException {
        em.persist(t);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        em.remove(em.find(DummyEntity.class, id));
    }

}
