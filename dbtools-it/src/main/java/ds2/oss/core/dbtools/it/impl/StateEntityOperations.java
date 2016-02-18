/**
 * 
 */
package ds2.oss.core.dbtools.it.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.dbtools.AbstractPersistenceSupportImpl;
import ds2.oss.core.dbtools.it.entities.StateEntity;

/**
 * @author dstrauss
 *         
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
}
