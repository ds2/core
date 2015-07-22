/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.dbtools.AbstractPersistenceSupportImpl#getEntityClass()
     */
    @Override
    protected Class<OptionValueDto<Long, ?>> getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
