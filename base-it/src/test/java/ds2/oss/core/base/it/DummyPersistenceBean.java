/**
 * 
 */
package ds2.oss.core.base.it;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import ds2.oss.core.api.PersistenceSupport;

/**
 * The persistence bean.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DummyPersistenceBean
    implements
    PersistenceSupport<DummyEntity, Long> {
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
    public void persist(@NotNull final DummyEntity t) {
        em.persist(t);
    }
    
    @Override
    public DummyEntity getById(@NotNull final Long e) {
        return em.find(DummyEntity.class, e);
    }
    
}
