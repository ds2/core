/*
 * Copyright 2012-2014 Dirk Strauss
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

import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.impl.ejb.AbstractOptionsPersistenceSupportBean;

/**
 * @author dstrauss
 * 
 */
@Stateless(name = "persistenceBean")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Alternative
public class DbPersistenceSupportBean extends AbstractOptionsPersistenceSupportBean
    implements
    NumberedOptionsPersistenceSupport {
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
    
    @Override
    public <V> OptionDto<Long, V> findOptionByIdentifier(final OptionIdentifier<V> ident) {
        return findOptionByIdentifier(em, ident);
    }
    
    @Override
    public void persist(final OptionDto<Long, ?> t) {
        t.setModifierName(ctx.getCallerPrincipal().getName());
        performPersist(em, t);
    }
    
    @Override
    public <V> OptionDto<Long, V> setOptionStage(final OptionIdentifier<V> ident, final OptionStage newStage) {
        return setOptionStage(em, ident, newStage);
    }
    
}
