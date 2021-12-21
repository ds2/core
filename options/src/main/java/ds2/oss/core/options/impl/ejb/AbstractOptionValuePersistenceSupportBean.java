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

import ds2.oss.core.api.dto.impl.OptionValueContextDto;
import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeType;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.*;
import ds2.oss.core.dbtools.AbstractPersistenceSupportImpl;
import ds2.oss.core.dbtools.modules.LifeCycleAwareModule;
import ds2.oss.core.dbtools.modules.LifeCycleAwareModule_;
import ds2.oss.core.options.api.NumberedOptionValuePersistenceSupport;
import ds2.oss.core.options.api.ValueTypeParser;
import ds2.oss.core.options.impl.entities.OptionEntity;
import ds2.oss.core.options.impl.entities.OptionEntity_;
import ds2.oss.core.options.impl.entities.OptionValueEntity;
import ds2.oss.core.options.impl.entities.OptionValueEntity_;
import ds2.oss.core.options.internal.OptionValueContextModule;
import ds2.oss.core.options.internal.OptionValueContextModule_;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A base class to support some common operations for dealing with option value persistence.
 *
 * @author dstrauss
 * @version 0.3
 */
public abstract class AbstractOptionValuePersistenceSupportBean
        extends
        AbstractPersistenceSupportImpl<OptionValueDto<Long, ?>, Long> implements NumberedOptionValuePersistenceSupport {

    /**
     * Returns the option context predicate.
     *
     * @param predicates the list to add predicates to
     * @param qb         the criteria builder
     * @param path       the path to the option value context module
     * @param ctx        the option value context
     */
    private static void getContextPredicate(List<Predicate> predicates, CriteriaBuilder qb,
                                            Path<OptionValueContextModule> path, OptionValueContext ctx) {
        if (ctx == null) {
            ctx = new OptionValueContextDto();
        }
        if (ctx.getCluster() != null) {
            predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule.COL_CLUSTER), Cluster.class, CLUSTER));
        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule.COL_CLUSTER)));
        }
        if (ctx.getConfiguration() != null) {
            predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule.COL_RTCONFIG),
                    RuntimeType.class, RT_CONFIG));
        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule.COL_RTCONFIG)));
        }
        if (ctx.getRequestedDomain() != null) {
            predicates.add(
                    getIsNullOrValue(qb, path.get(OptionValueContextModule.COL_REQDOMAIN), String.class, REQ_DOMAIN));
        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule.COL_REQDOMAIN)));
        }
        if (ctx.getServer() != null) {
            ServerIdentifier si = ctx.getServer();
            if (si.getDomain() != null) {
                predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule.COL_SERVERDOMAIN), String.class,
                        SERVER_DOMAIN));
            }
            if (si.getHostName() != null) {
                predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule.COL_SERVERHOSTNAME), String.class,
                        SERVER_HOSTNAME));
            }
            if (si.getIpAddress() != null) {
                predicates
                        .add(getIsNullOrValue(qb, path.get(OptionValueContextModule.COL_SERVERADDR), String.class, SERVER_IP));
            }

        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule.COL_SERVERDOMAIN)));
            predicates.add(qb.isNull(path.get(OptionValueContextModule.COL_SERVERHOSTNAME)));
            predicates.add(qb.isNull(path.get(OptionValueContextModule.COL_SERVERADDR)));
        }
    }

    /**
     * Returns a predicate to support null or a value on a specific parameter.
     *
     * @param cb        the criteria builder
     * @param p         the path to the attribute
     * @param c         the param value class
     * @param paramName the JQL parameter name
     * @return the predicate
     */
    private static <V> Predicate getIsNullOrValue(CriteriaBuilder cb, Path<V> p, Class<V> c, String paramName) {
        Predicate isNull = cb.isNull(p);
        Predicate isValue = cb.equal(p, cb.parameter(c, paramName));
        return cb.or(isValue, isNull);
    }

    /**
     * Returns a predicate that checks if the given life cycle applies.
     *
     * @param cb    the criteria builder
     * @param p     the path to the life cycle aware module
     * @param value the value of the date parameter
     * @return the predicate to use
     */
    private static Predicate getLcaPredicate(CriteriaBuilder cb, Path<LifeCycleAwareModule> p, String value) {
        Predicate rc = null;
        Predicate lessThan =
                cb.lessThanOrEqualTo(p.get(LifeCycleAwareModule.COL_VALIDFROM), cb.parameter(LocalDateTime.class, value));
        // and
        Predicate isNull = cb.isNull(p.get(LifeCycleAwareModule.COL_VALIDTO));
        Predicate greaterThan =
                cb.greaterThanOrEqualTo(p.get(LifeCycleAwareModule.COL_VALIDTO), cb.parameter(LocalDateTime.class, value));
        Predicate endDate = cb.or(isNull, greaterThan);
        rc = cb.and(lessThan, endDate);
        // lessThan and (isNull or greaterThan)
        return rc;
    }

    /**
     * SERVER_IP of type String.
     */
    private static final String SERVER_IP = "serverIp";
    /**
     * SERVER_HOSTNAME of type String.
     */
    private static final String SERVER_HOSTNAME = "serverHostname";
    /**
     * SERVER_DOMAIN of type String.
     */
    private static final String SERVER_DOMAIN = "serverDomain";
    /**
     * REQ_DOMAIN of type String.
     */
    private static final String REQ_DOMAIN = "reqDomain";
    /**
     * RT_CONFIG of type String.
     */
    private static final String RT_CONFIG = "rtConfig";
    /**
     * CLUSTER of type String.
     */
    private static final String CLUSTER = "cluster";

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

    /**
     * Finds the best matching option value.
     *
     * @param em    the entity manager
     * @param ident the option identifier
     * @param ctx   the access context
     * @return the found option value, or null if no value has been found
     */
    public <V> OptionValue<Long, V> findBestOptionValue(EntityManager em, OptionIdentifier<V> ident,
                                                        OptionValueContext ctx) {
        // find option
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OptionValueEntity> cq = cb.createQuery(OptionValueEntity.class);
        Root<OptionValueEntity> optionValueRoot = cq.from(OptionValueEntity.class);
        Root<OptionEntity> optionRoot = null;
        // Join<OptionValueEntity, OptionEntity> optionJoin =
        // optionValueRoot.join(OptionValueEntity_.refOption);
        cq.select(optionValueRoot);
        // setup restrictions
        List<Predicate> restrictions = new ArrayList<>();
        // restrictions.add(qb.equal(optionJoin.get(OptionEntity_.stage), OptionStage.Online));
        // restrictions.add(qb.equal(optionJoin.get(OptionEntity_.optionName),
        // qb.parameter(String.class,
        // "optionName")));
        // restrictions.add(qb.equal(optionJoin.get(OptionEntity_.applicationName),qb.parameter(String.class,
        // "applicationName")));

        Subquery<OptionEntity> optionQuery = cq.subquery(OptionEntity.class);
        optionRoot = optionQuery.from(OptionEntity.class);
        optionQuery.select(optionRoot);

        List<Predicate> optionPredicates = new ArrayList<Predicate>();
        optionPredicates.add(
                cb.equal(optionRoot.get(OptionEntity.APP_NAME), cb.parameter(String.class, "applicationName")));
        optionPredicates
                .add(cb.equal(optionRoot.get(OptionEntity.NAME), cb.parameter(String.class, "optionName")));
        optionPredicates.add(cb.equal(optionRoot.get(OptionEntity.STAGE), OptionStage.Online));
        optionQuery.where(optionPredicates.toArray(new Predicate[optionPredicates.size()]));
        /*
         * restrictions.add(qb.equal(optionValueRoot.get(OptionValueEntity_.refOption).get(
         * OptionEntity_ .id), qb.parameter(Long.class, "optionId")));
         */
        /*
         * restrictions.add(qb.equal(optionValueRoot.get(OptionValueEntity_.refOption).get("id"),
         * qb.parameter(Long.class, "optionId")));
         */
        restrictions.add(cb.equal(optionValueRoot.get(OptionValueEntity.REF_OPTION), optionQuery));
        restrictions.add(cb.equal(optionValueRoot.get(OptionValueEntity.STAGE), OptionValueStage.Live));
        restrictions.add(getLcaPredicate(cb, optionValueRoot.get(OptionValueEntity_.lca), "date"));
        getContextPredicate(restrictions, cb, optionValueRoot.get(OptionValueEntity_.ctx), ctx);
        cq.where(restrictions.toArray(new Predicate[restrictions.size()]));
        List<Order> orderByList = new ArrayList<Order>(4);
        orderByList.add(cb.desc(optionValueRoot.get(OptionValueEntity_.ctx).get(OptionValueContextModule.COL_CLUSTER)));
        orderByList
                .add(cb.desc(optionValueRoot.get(OptionValueEntity_.ctx).get(OptionValueContextModule.COL_RTCONFIG)));
        orderByList
                .add(cb.desc(optionValueRoot.get(OptionValueEntity_.ctx).get(OptionValueContextModule.COL_REQDOMAIN)));
        orderByList
                .add(cb.desc(optionValueRoot.get(OptionValueEntity_.ctx).get(OptionValueContextModule.COL_SERVERHOSTNAME)));
        cq.orderBy(orderByList.toArray(new Order[orderByList.size()]));
        // perform query to database
        TypedQuery<OptionValueEntity> query = em.createQuery(cq);
        // query.setParameter("optionId", optionId);
        query.setParameter("applicationName", ident.getApplicationName());
        query.setParameter("optionName", ident.getOptionName());
        query.setParameter("date", LocalDateTime.now());
        if (ctx.getCluster() != null) {
            query.setParameter(CLUSTER, ctx.getCluster());
        }
        if (ctx.getConfiguration() != null) {
            query.setParameter(RT_CONFIG, ctx.getConfiguration());
        }
        if (ctx.getRequestedDomain() != null) {
            query.setParameter(REQ_DOMAIN, ctx.getRequestedDomain());
        }
        if (ctx.getServer() != null) {
            ServerIdentifier si = ctx.getServer();
            if (si.getDomain() != null) {
                query.setParameter(SERVER_DOMAIN, si.getDomain());
            }
            if (si.getHostName() != null) {
                query.setParameter(SERVER_HOSTNAME, si.getHostName());
            }
            if (si.getIpAddress() != null) {
                query.setParameter(SERVER_IP, si.getIpAddress());
            }
        }
        OptionValueEntity foundOptionValue = getSecureSingle(query);
        OptionValue<Long, V> rc = parser.toDto(foundOptionValue);
        return rc;
    }

    /**
     * Finds an option.
     *
     * @param em              the entity manager
     * @param optionReference the id of the option
     * @return the found option, or null if not found
     */
    private Option<Long, ?> findOptionById(EntityManager em, Long optionReference) {
        return getSecureFindByIdInternal(em, OptionEntity.class, optionReference);
    }

    /**
     * Returns the option value with the given id.
     *
     * @param em the entity manager
     * @param id the id of the option value entity
     * @return the option value dto
     */
    public <V> OptionValueDto<Long, V> performGetById(EntityManager em, Long id) {
        final OptionValueEntity foundEntity = getSecureFindByIdInternal(em, OptionValueEntity.class, id);
        return parser.toDto(foundEntity);
    }

    /**
     * Persists the given data.
     *
     * @param em the entity manager
     * @param t  the option value
     */
    protected void performPersist(final EntityManager em, final OptionValueDto<Long, ?> t) {
        LOG.debug("Trying to persist given dto {}", t);
        final Set<?> valResult = val.validate(t);
        if (valResult.size() > 0) {
            LOG.warn("Given dto has errors and cannot be used: {}", valResult);
            throw new IllegalArgumentException("Given DTO has validation errors!");
        }
        final OptionValueEntity e = new OptionValueEntity();
        e.setApproverName(t.getApproverName());
        e.setAuthorName(t.getAuthorName());
        e.setCreatedBy(t.getCreatedBy());
        e.setStage(t.getStage());
        e.setCluster(t.getCluster());
        e.setConfiguration(t.getConfiguration());
        e.setEncoded(t.getEncoded());
        e.setInitVector(t.getInitVector());
        e.setRefOption(findOptionById(em, t.getOptionReference()));
        e.setRequestedDomain(t.getRequestedDomain());
        e.setServer(t.getServer());
        e.setValidFrom(t.getValidFrom());
        e.setValidTo(t.getValidTo());
        e.setValue(parser.toString(t.getValueType(), t.getValue()));
        e.setValueType(t.getValueType());
        em.persist(e);
        LOG.debug("Persisted option is {}", e);
        t.setId(e.getId());
        t.setCreated(e.getCreated());
        t.setModified(e.getModified());
    }

}
