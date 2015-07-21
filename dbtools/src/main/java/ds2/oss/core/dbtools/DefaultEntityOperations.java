/**
 * 
 */
package ds2.oss.core.dbtools;

import javax.persistence.EntityManager;

import ds2.oss.core.api.Persistable;

/**
 * @author dstrauss
 * @param <E>
 *            the persistable entity
 *            
 */
public abstract class DefaultEntityOperations<E extends Persistable<Long>> {
    public E getById(EntityManager em, long id) {
        return em.find(getEntityClass(), Long.valueOf(id));
    }
    
    /**
     * This will create a new db entry using the given entity bean. Update operations are done
     * implicit by loading an entity and updating its values.
     * 
     * @param em
     *            the entity manager
     * @param e
     *            the entity to create
     */
    public void create(EntityManager em, E e) {
        em.persist(e);
    }
    
    protected abstract Class<E> getEntityClass();
}
