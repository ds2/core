/*
 * Copyright 2012-2013 Dirk Strauss
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
package ds2.oss.core.options.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.options.api.ValueTypeParser;
import ds2.oss.core.options.impl.dto.OptionEntity;

/**
 * An abstract EJB to be used for database transactional wrapping.
 * 
 * @author dstrauss
 * @version 0.3
 */
public abstract class AbstractOptionsPersistenceSupportBean implements PersistenceSupport<Option<Long, Object>, Long> {
    /**
     * The parser.
     */
    @Inject
    private ValueTypeParser parser;
    
    /**
     * Persists a given entry into the database.
     * 
     * @param em
     *            the entity manager
     * @param t
     *            the option to store
     */
    protected void performPersist(final EntityManager em, final Option<Long, ?> t) {
        final OptionEntity ent = new OptionEntity();
        ent.setApplicationName(t.getApplicationName());
        ent.setDefaultValue(parser.toString(t.getValueType(), t.getDefaultValue()));
        ent.setEncrypted(t.isEncrypted());
        ent.setModifierName("TODO");
        ent.setOptionName(t.getOptionName());
        ent.setValueType(t.getValueType());
        ent.setStage(t.getStage());
        em.persist(ent);
    }
}
