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

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.Persistable;

/**
 * @author dstrauss
 * @param <E>
 *            the persistable entity
 *            
 */
@Deprecated
public abstract class DefaultEntityOperations<E extends Persistable<Long>> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    public E getByIdLong(EntityManager em, long id) {
        return em.find(getEntityClass(), Long.valueOf(id));
    }
    
    public E getByIdInt(EntityManager em, int id) {
        return em.find(getEntityClass(), Integer.valueOf(id));
    }
    
    public List<E> getAll(EntityManager em, int size) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(getEntityClass());
        Root<E> rootE = cq.distinct(true).from(getEntityClass());
        TypedQuery<E> q = em.createQuery(cq);
        return getSecureList(q);
    }
    
    protected static <E> List<E> getSecureList(final TypedQuery<E> q) {
        List<E> rc = null;
        try {
            rc = q.getResultList();
        } catch (final IllegalStateException e) {
            LOG.debug("Error when executing the given query! Returning null.", e);
        } catch (final NoResultException e) {
            LOG.debug("Given query returned no result! Ignoring.", e);
            rc = new ArrayList<>(0);
        }
        return rc;
    }
    
    /**
     * This will create a new db entry using the given entity bean. Update operations are done
     * implicit by loading an entity and updating its values.
     * 
     * the entity manager
     * 
     * @param e
     *            the entity to create
     */
    public void create(EntityManager em, E e) {
        em.persist(e);
    }
    
    protected abstract Class<E> getEntityClass();
}
