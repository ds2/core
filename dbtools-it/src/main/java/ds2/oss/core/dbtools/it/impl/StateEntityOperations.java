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
import ds2.oss.core.dbtools.DefaultEntityOperations;
import ds2.oss.core.dbtools.it.entities.StateEntity;

/**
 * @author dstrauss
 *         
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class StateEntityOperations extends DefaultEntityOperations<StateEntity> {
    @PersistenceContext(unitName = "octest")
    private EntityManager em;
    
    public EntryState getById(long l) {
        return getById(em, l);
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.dbtools.DefaultEntityOperations#getEntityClass()
     */
    @Override
    protected Class<StateEntity> getEntityClass() {
        return StateEntity.class;
    }
}
