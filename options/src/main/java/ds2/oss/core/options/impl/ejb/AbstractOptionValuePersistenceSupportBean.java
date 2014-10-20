package ds2.oss.core.options.impl.ejb;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.dto.impl.OptionValueContextDto;
import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.base.impl.db.AbstractPersistenceSupportImpl;
import ds2.oss.core.base.impl.db.LifeCycleAwareModule;
import ds2.oss.core.base.impl.db.LifeCycleAwareModule_;
import ds2.oss.core.options.api.NumberedOptionValuePersistenceSupport;
import ds2.oss.core.options.api.ValueTypeParser;
import ds2.oss.core.options.impl.entities.OptionEntity;
import ds2.oss.core.options.impl.entities.OptionValueEntity;
import ds2.oss.core.options.impl.entities.OptionValueEntity_;
import ds2.oss.core.options.internal.OptionValueContextModule;
import ds2.oss.core.options.internal.OptionValueContextModule_;

/**
 * A base class to support some common operations for dealing with option value persistence.
 *
 * @author dstrauss
 * @version 0.3
 *
 */
public abstract class AbstractOptionValuePersistenceSupportBean
    extends
    AbstractPersistenceSupportImpl<OptionValueDto<Long, ?>, Long> implements NumberedOptionValuePersistenceSupport {
    
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
     * Persists the given data.
     *
     * @param em
     *            the entity manager
     * @param t
     *            the option value
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
        e.setStage(t.getStage());
        e.setCluster(t.getCluster());
        e.setConfiguration(t.getConfiguration());
        e.setCreated(new Date());
        e.setModified(e.getCreated());
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
    
    /**
     * Finds an option.
     *
     * @param em
     *            the entity manager
     * @param optionReference
     *            the id of the option
     * @return the found option, or null if not found
     */
    private Option<Long, ?> findOptionById(EntityManager em, Long optionReference) {
        return getSecureFindById(em, OptionEntity.class, optionReference);
    }
    
    public <V> OptionValueDto<Long, V> performGetById(EntityManager em, Long id, Class<V> c) {
        final OptionValueEntity foundEntity = getSecureFindById(em, OptionValueEntity.class, id);
        return parser.toDto(foundEntity, c);
    }
    
    /**
     * Finds the best matching option value.
     * 
     * @param em
     *            the entity manager
     * @param ident
     *            the option identifier
     * @param ctx
     *            the access context
     * @return the found option value, or null if no value has been found
     */
    public <V> OptionValue<Long, V> findBestOptionValue(EntityManager em, OptionIdentifier<V> ident,
        OptionValueContext ctx) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<OptionValueEntity> cq = qb.createQuery(OptionValueEntity.class);
        Root<OptionValueEntity> optionValueRoot = cq.from(OptionValueEntity.class);
        cq.select(optionValueRoot);
        // setup restrictions
        List<Predicate> restrictions = new ArrayList<>();
        restrictions.add(qb.equal(optionValueRoot.get("stage"), OptionValueStage.Live));
        Date now = new Date();
        restrictions.add(getLcaPredicate(qb, optionValueRoot.get(OptionValueEntity_.lca), "date"));
        getContextPredicate(restrictions, qb, optionValueRoot.get(OptionValueEntity_.ctx), ctx);
        // perform query to database
        cq.where(restrictions.toArray(new Predicate[restrictions.size()]));
        TypedQuery<OptionValueEntity> query = em.createQuery(cq);
        query.setParameter("date", now);
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
        OptionValue<Long, V> rc = parser.toDto(foundOptionValue, null);
        return rc;
    }
    
    /**
     * Returns the option context predicate.
     * 
     * @param predicates
     *            the list to add predicates to
     * @param qb
     *            the criteria builder
     * @param path
     *            the path to the option value context module
     * @param ctx
     *            the option value context
     */
    private static void getContextPredicate(List<Predicate> predicates, CriteriaBuilder qb,
        Path<OptionValueContextModule> path, OptionValueContext ctx) {
        if (ctx == null) {
            ctx = new OptionValueContextDto();
        }
        if (ctx.getCluster() != null) {
            predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule_.cluster), Cluster.class, CLUSTER));
        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule_.cluster)));
        }
        if (ctx.getConfiguration() != null) {
            predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule_.configuration),
                RuntimeConfiguration.class, RT_CONFIG));
        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule_.configuration)));
        }
        if (ctx.getRequestedDomain() != null) {
            predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule_.requestedDomain), String.class,
                REQ_DOMAIN));
        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule_.requestedDomain)));
        }
        if (ctx.getServer() != null) {
            ServerIdentifier si = ctx.getServer();
            if (si.getDomain() != null) {
                predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule_.serverDomain), String.class,
                    SERVER_DOMAIN));
            }
            if (si.getHostName() != null) {
                predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule_.serverHostname), String.class,
                    SERVER_HOSTNAME));
            }
            if (si.getIpAddress() != null) {
                predicates.add(getIsNullOrValue(qb, path.get(OptionValueContextModule_.serverIp), String.class,
                    SERVER_IP));
            }
            
        } else {
            predicates.add(qb.isNull(path.get(OptionValueContextModule_.serverDomain)));
            predicates.add(qb.isNull(path.get(OptionValueContextModule_.serverHostname)));
            predicates.add(qb.isNull(path.get(OptionValueContextModule_.serverIp)));
        }
    }
    
    /**
     * Returns a predicate to support null or a value on a specific parameter.
     * 
     * @param cb
     *            the criteria builder
     * @param p
     *            the path to the attribute
     * @param c
     *            the param value class
     * @param paramName
     *            the JQL parameter name
     * @return the predicate
     */
    private static <V> Predicate getIsNullOrValue(CriteriaBuilder cb, Path<V> p, Class<V> c, String paramName) {
        Predicate isNull = cb.isNull(p);
        Predicate isValue = cb.equal(p, cb.parameter(c, paramName));
        return cb.or(isNull, isValue);
    }
    
    /**
     * Returns a predicate that checks if the given life cycle applies.
     * 
     * @param cb
     *            the criteria builder
     * @param p
     *            the path to the life cycle aware module
     * @param value
     *            the value of the date parameter
     * @return the predicate to use
     */
    private static Predicate getLcaPredicate(CriteriaBuilder cb, Path<LifeCycleAwareModule> p, String value) {
        Predicate rc = null;
        Predicate lessThan =
            cb.lessThanOrEqualTo(p.get(LifeCycleAwareModule_.validFrom), cb.parameter(Date.class, value));
        // and
        Predicate isNull = cb.isNull(p.get(LifeCycleAwareModule_.validTo));
        Predicate greaterThan =
            cb.greaterThanOrEqualTo(p.get(LifeCycleAwareModule_.validTo), cb.parameter(Date.class, value));
        Predicate endDate = cb.or(isNull, greaterThan);
        rc = cb.and(lessThan, endDate);
        // lessThan and (isNull or greaterThan)
        return rc;
    }
}
