/**
 * 
 */
package ds2.oss.core.options.it;

import java.util.Date;

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

import ds2.oss.core.api.dto.impl.OptionValueContextDto;
import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.options.impl.ejb.AbstractOptionValuePersistenceSupportBean;
import ds2.oss.core.options.impl.entities.OptionValueEntity;

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
        return performGetById(em, e);
    }
    
    @Override
    public void setStage(Long id, OptionValueStage newStage) {
        OptionValueEntity entity = getSecureFindById(em, OptionValueEntity.class, id);
        entity.setStage(newStage);
        entity.setModified(new Date());
        entity.setApproverName(ctx.getCallerPrincipal().getName());
    }
    
    @Override
    public <V> OptionValue<Long, V> findBestOptionValue(OptionIdentifier<V> ident, OptionValueContext ctx) {
        if (ctx == null) {
            ctx = new OptionValueContextDto();
        }
        // find option by ident
        return findBestOptionValue(em, ident, ctx);
    }
    
}
