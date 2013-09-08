/**
 * 
 */
package ds2.oss.core.options.it;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.options.api.OptionsPersistenceSupport;
import ds2.oss.core.options.impl.AbstractOptionsPersistenceSupportBean;

/**
 * @author dstrauss
 * 
 */
@Stateless(name = "persistenceBean")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@OptionsPersistenceSupport
public class PersistenceSupportBean extends AbstractOptionsPersistenceSupportBean {
    /**
     * The entity manager.
     */
    @PersistenceContext(unitName = "corePU")
    private EntityManager em;
    
    @Override
    public void persist(final Option<Long, Object> t) {
        performPersist(em, t);
    }
    
    @Override
    public Option<Long, Object> getById(final Long e) {
        return null;
    }
    
}
