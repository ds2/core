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
import ds2.oss.core.dbtools.it.StateService;
import ds2.oss.core.dbtools.it.entities.StateEntity;

/**
 * @author dstrauss
 *
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class StateServiceBean implements StateService {
    @PersistenceContext(unitName = "octest")
    private EntityManager em;
    
    @Override
    public EntryState createStateByName(String s) {
        StateEntity se = new StateEntity(s);
        em.persist(se);
        return se;
    }
    
}
