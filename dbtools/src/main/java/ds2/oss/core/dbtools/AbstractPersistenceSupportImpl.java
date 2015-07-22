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
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.Persistable;
import ds2.oss.core.api.PersistenceSupport;

/**
 * An abstract persistence support using the idea of a DAO implementation.
 *
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the entity type
 * @param <PRIMKEY>
 *            the primary key type
 */
public abstract class AbstractPersistenceSupportImpl<E extends Persistable<PRIMKEY>, PRIMKEY>
    implements
    PersistenceSupport<E, PRIMKEY> {
    
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * @return the entity class
     */
    protected abstract Class<E> getEntityClass();
    
    /**
     * Returns a list of a given query.
     *
     * @param q
     *            the query
     * @param entityClass
     *            the entity class
     * @param <E>
     *            the entity type
     * @return the found results, or null if nothing was found
     * @deprecated Better use the {@link #getSecureList(javax.persistence.TypedQuery)} method
     *             instead.
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    protected static <E> List<E> getSecureList(final Query q, final Class<E> entityClass) {
        List<E> rc = null;
        try {
            rc = q.getResultList();
        } catch (final IllegalStateException e) {
            LOG.debug("Error when executing the given query! Returning null.", e);
        }
        return rc;
    }
    
    /**
     * Returns the list result based on the given query. This method will catch any possibly
     * occuring exceptions, assuring that the business logic is not affected.
     * 
     * @param q
     *            the query
     * @return null, or a list of found entries
     */
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
     * Returns a single item from a given query.
     *
     * @param q
     *            the query
     * @param entityClass
     *            the target entity class
     * @param <E>
     *            the entity type
     * @return the found item, or null if not found
     * @deprecated This method is not type safe.
     */
    @Deprecated
    protected static <E> E getSecureSingle(final Query q, final Class<E> entityClass) {
        E rc = null;
        try {
            rc = entityClass.cast(q.getSingleResult());
        } catch (final IllegalStateException e) {
            LOG.debug("Error when executing the given query! Returning null.", e);
        } catch (final NoResultException e) {
            LOG.debug("Given query returned no result! Ignoring.", e);
        } catch (final NonUniqueResultException e) {
            LOG.warn("Given query does not return one but several results! Returning only first item.", e);
            rc = getSecureList(q, entityClass).get(0);
        }
        return rc;
    }
    
    /**
     * Returns the unique result based on the given query.
     * 
     * @param q
     *            the unique result
     * @return the found dto, or null
     */
    @Deprecated
    public E getSecureSingle(final Query q) {
        return getSecureSingle(q, getEntityClass());
    }
    
    /**
     * Returns the unique result based on the given query.
     * 
     * @param q
     *            the unique result
     * @return the found dto, or null
     */
    protected static <E> E getSecureSingle(final TypedQuery<E> q) {
        E rc = null;
        try {
            rc = q.getSingleResult();
        } catch (final IllegalStateException e) {
            LOG.debug("Error when executing the given query! Returning null.", e);
        } catch (final NoResultException e) {
            LOG.debug("Given query returned no result! Ignoring.", e);
        } catch (final NonUniqueResultException e) {
            LOG.warn("Given query does not return one but several results! Returning only first item.", e);
            rc = getSecureList(q).get(0);
        }
        return rc;
    }
    
    /**
     * Finds a specific entity.
     *
     * @param em
     *            the entity manager
     * @param c
     *            the target entity class
     * @param id
     *            the id of the entity
     * @return the entity, or null if not found
     */
    protected <T extends Persistable<PRIMKEY>> T getSecureFindById(final EntityManager em, final Class<T> c,
        final PRIMKEY id) {
        if (id == null) {
            LOG.warn("No primary key given to search for. Ignoring request!");
            return null;
        }
        T rc = null;
        try {
            rc = em.find(c, id);
        } catch (final IllegalArgumentException e) {
            LOG.debug("Error when finding an entity!", e);
        }
        return rc;
    }
    
    /**
     * Finds the entity with the given primary key.
     * 
     * @param em
     *            the entity manager
     * @param id
     *            the primary key
     * @return the found entity, or null if not found
     */
    public E getSecureFindById(final EntityManager em, final PRIMKEY id) {
        return getSecureFindById(em, getEntityClass(), id);
    }
    
    /**
     * Returns all entries.
     * 
     * @param em
     *            the entity manager
     * @param size
     *            the maximum size. Use 0 to return ALL entries.
     * @return the found entries
     */
    protected List<E> getAll(EntityManager em, int size) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(getEntityClass());
        Root<E> rootE = cq.distinct(true).from(getEntityClass());
        cq.select(rootE);
        TypedQuery<E> q = em.createQuery(cq);
        if (size > 0) {
            q.setMaxResults(size);
        }
        return getSecureList(q);
    }
    
    /**
     * This will create a new db entry using the given entity bean. Update operations are done
     * implicit by loading an entity and updating its values.
     * 
     * the entity manager
     * 
     * @param em
     *            the entity manager
     * @param e
     *            the entity to store
     */
    protected void create(EntityManager em, E e) {
        em.persist(e);
    }
}
