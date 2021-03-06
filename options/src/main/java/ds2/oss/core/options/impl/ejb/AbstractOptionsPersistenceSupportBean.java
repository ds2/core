/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.options.impl.ejb;

import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.dbtools.AbstractPersistenceSupportImpl;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.api.ValueTypeParser;
import ds2.oss.core.options.impl.entities.OptionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Validator;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * An abstract EJB to be used for database transactional wrapping.
 *
 * @author dstrauss
 * @version 0.3
 */
public abstract class AbstractOptionsPersistenceSupportBean
        extends
        AbstractPersistenceSupportImpl<OptionDto<Long, ?>, Long> implements NumberedOptionsPersistenceSupport {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The parser.
     */
    @Inject
    private ValueTypeParser parser;
    /**
     * The platform validator.
     */
    @Inject
    private Validator val;

    @Override
    public OptionDto<Long, Object> getById(final Long e) {
        throw new UnsupportedOperationException(
                "getById is not supported for options! Use the OptionIdentifier instead.");
    }

    /**
     * Persists a given entry into the database.
     *
     * @param em the entity manager
     * @param t  the option to store
     */
    protected void performPersist(final EntityManager em, final OptionDto<Long, ?> t) {
        LOG.debug("Trying to persist given dto {}", t);
        final Set<?> valResult = val.validate(t);
        if (valResult.size() > 0) {
            LOG.warn("Given dto has errors and cannot be used: {}", valResult);
            throw new IllegalArgumentException("Given DTO has validation errors!");
        }
        final OptionEntity ent = new OptionEntity();
        ent.setApplicationName(t.getApplicationName());
        ent.setDefaultValue(parser.toString(t.getValueType(), t.getDefaultValue()));
        ent.setEncrypted(t.isEncrypted());
        ent.setModified(LocalDateTime.now());
        ent.setModifiedBy(t.getModifiedBy());
        ent.setCreated(t.getCreated());
        ent.setCreatedBy(t.getCreatedBy());
        ent.setOptionName(t.getOptionName());
        ent.setValueType(t.getValueType());
        ent.setStage(t.getStage());
        ent.setDescription(t.getDescription());
        ent.setEncoded(t.getEncoded());
        ent.setInitVector(t.getInitVector());
        em.persist(ent);
        LOG.debug("Persisted option is {}", ent);
        t.setId(ent.getId());
        t.setCreated(ent.getCreated());
        t.setModified(ent.getModified());
        t.setModifiedBy(ent.getModifiedBy());
    }

    /**
     * Finds an option.
     *
     * @param em    the entity manager
     * @param ident the option identifier
     * @param <V>   the value of the option
     * @return the found option
     */
    protected <V> OptionDto<Long, V> findOptionByIdentifier(final EntityManager em, final OptionIdentifier<V> ident) {
        TypedQuery<OptionEntity> q = em.createNamedQuery(QUERY_FINDOPTIONBYIDENTIFIER, OptionEntity.class);
        q.setParameter("optionName", ident.getOptionName());
        q.setParameter("appName", ident.getApplicationName());
        q.setMaxResults(1);
        final OptionEntity foundOption = getSecureSingle(q);
        LOG.debug("Found entity is {}", foundOption);
        return parser.toDto(foundOption, ident);
    }

    /**
     * Sets a new option stage for a given option.
     *
     * @param em       the entity manager
     * @param ident    the option identifier
     * @param newStage the new stage value
     * @param <V>      the value of the option
     * @return the updated option
     */
    protected <V> OptionDto<Long, V> setOptionStage(final EntityManager em, final OptionIdentifier<V> ident,
                                                    final OptionStage newStage) {
        final TypedQuery<OptionEntity> q = em.createNamedQuery(QUERY_FINDOPTIONBYIDENTIFIER, OptionEntity.class);
        q.setParameter("optionName", ident.getOptionName());
        q.setParameter("appName", ident.getApplicationName());
        q.setMaxResults(1);
        final OptionEntity foundOption = getSecureSingle(q);
        foundOption.setStage(newStage);
        em.merge(foundOption);
        return parser.toDto(foundOption, ident);
    }

}
