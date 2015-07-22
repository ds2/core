/**
 * 
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
