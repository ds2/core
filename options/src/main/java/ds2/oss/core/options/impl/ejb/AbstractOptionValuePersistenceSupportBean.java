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
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.base.impl.db.AbstractPersistenceSupportImpl;
import ds2.oss.core.base.impl.db.LifeCycleAwareModule_;
import ds2.oss.core.options.api.NumberedOptionValuePersistenceSupport;
import ds2.oss.core.options.api.ValueTypeParser;
import ds2.oss.core.options.impl.entities.OptionEntity;
import ds2.oss.core.options.impl.entities.OptionValueEntity;
import ds2.oss.core.options.impl.entities.OptionValueEntity_;

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
        Root<OptionEntity> optionRoot = cq.from(OptionEntity.class);
        ParameterExpression<OptionValueStage> p1 = qb.parameter(OptionValueStage.class);
        cq.select(optionValueRoot);
        // setup restrictions
        List<Predicate> restrictions = new ArrayList<>();
        restrictions.add(qb.equal(optionValueRoot.get("stage"), OptionValueStage.Live));
        Predicate predicate2;
        Date now = new Date();
        restrictions.add(qb.lessThanOrEqualTo(
            optionValueRoot.get(OptionValueEntity_.lca).get(LifeCycleAwareModule_.validFrom), now));
        
        // perform query to database
        cq.where(restrictions.toArray(new Predicate[restrictions.size()]));
        TypedQuery<OptionValueEntity> query = em.createQuery(cq);
        OptionValueEntity foundOptionValue = getSecureSingle(query);
        OptionValue<Long, V> rc = parser.toDto(foundOptionValue, null);
        return rc;
    }
}
