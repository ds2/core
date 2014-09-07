/**
 * 
 */
package ds2.oss.core.options.it;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.options.impl.ejb.AbstractOptionValuePersistenceSupportBean;

/**
 * @author dstrauss
 *
 */
@Stateless(name = "optionValuePersistence")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Transactional
@Alternative
public class DbOptionValuePersistenceBean extends AbstractOptionValuePersistenceSupportBean {
    /**
     * The entity manager.
     */
    @PersistenceContext(unitName = "corePU")
    private EntityManager em;
    /**
     * The invoker context.
     */
    @Resource
    private EJBContext ctx;
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(OptionValueDto<Long, ?> t) {
        t.setAuthorName(ctx.getCallerPrincipal().getName());
        performPersist(em, t);
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public OptionValueDto<Long, ?> getById(Long e) {
        return performGetById(em, e, null);
    }
    
}
