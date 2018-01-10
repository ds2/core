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
package ds2.oss.core.options.it;

import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.impl.ejb.AbstractOptionsPersistenceSupportBean;
import ds2.oss.core.options.impl.entities.OptionEntity;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 * @author dstrauss
 */
@Stateless(name = "persistenceBean")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Transactional
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
    public void deleteById(Long id) {
        em.remove(em.find(OptionEntity.class, id));
    }

    @Override
    public <V> OptionDto<Long, V> setOptionStage(final OptionIdentifier<V> ident, final OptionStage newStage) {
        return setOptionStage(em, ident, newStage);
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.dbtools.AbstractPersistenceSupportImpl#getEntityClass()
     */
    @Override
    protected Class<OptionDto<Long, ?>> getEntityClass() {
        // TODO Auto-generated method stub
        return null;
    }

}
