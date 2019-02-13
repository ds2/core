/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.api.persistence;

import ds2.oss.core.api.IdAware;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to help with some JPA operations.
 *
 * @author dstrauss
 * @version 0.1
 */
public interface JpaSupport {
    Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    /**
     * Deletes the given entity.
     *
     * @param em the entity manager to use
     * @param e  the entity to delete
     * @return TRUE
     */
    static <E extends IdAware<?>> boolean deleteEntity(
            final EntityManager em, final E e) {
        em.remove(e);
        return true;
    }

    static <E extends IdAware<?>> boolean deleteEntity(
            final EntityManager em, Class<E> entityClass, Object id) {
        E entity = em.find(entityClass, id);
        if (entity != null) {
            em.remove(entity);
            return true;
        }
        return false;
    }

    /**
     * Finds the persistable object by the given id.
     *
     * @param em the entity manager to use
     * @param c  the class of the entity to find
     * @param id the id
     * @return the found entity, or null if not found
     */
    static <E extends IdAware<?>> E findById(
            final EntityManager em, final Class<E> c, @NotNull final Object id) {
        E rc = em.find(c, id);
        return rc;
    }

    /**
     * Creates an entity.
     *
     * @param <E> the persistable type
     * @param em  the entity manager
     * @param e   the entity to persist
     * @return the updated entity
     */
    static <E extends IdAware<?>> E storeEntity(
            final EntityManager em, final E e) {
        final E rc = e;
        try {
            em.persist(e);
        } catch (EntityExistsException e2) {
            LOG.log(Level.FINE, "This entity already exists!", e2);
            throw e2;
        } catch (RuntimeException e2) {
            LOG.log(Level.FINE, "A strange runtime error occurred!", e2);
            throw e2;
        }
        return rc;
    }

    /**
     * Idea for an update method.
     *
     * @param em the entity manager
     * @param e  the entity to update
     * @return the updated entity
     */
    static <E extends IdAware<?>> E updateEntity(
            final EntityManager em, final E e) {
        E rc = e;
        rc = em.merge(rc);
        return rc;
    }

    /**
     * Returns the given entities.
     *
     * @param em          the entity manager to use
     * @param entityClass the entity class to return
     * @param start       the start index. Usually 0.
     * @param size        the count of entities to return. Usually 10.
     * @param <E>         the type of the entity
     * @return the list of found entities
     */
    static <E extends IdAware<?>> List<E> iterateOverAll(EntityManager em, Class<E> entityClass, int start, int size) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<E> thisQuery = criteriaBuilder.createQuery(entityClass);
        Root<E> entityRoot = thisQuery.from(entityClass);
        thisQuery.orderBy(criteriaBuilder.desc(entityRoot.get("id")));
        TypedQuery<E> dbQuery = em.createQuery(thisQuery);
        dbQuery.setMaxResults(Math.max(size, 1));
        dbQuery.setFirstResult(Math.max(start, 0));
        return dbQuery.getResultList();
    }

}
