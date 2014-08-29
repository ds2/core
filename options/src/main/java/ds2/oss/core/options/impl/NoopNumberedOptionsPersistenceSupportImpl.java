/**
 * 
 */
package ds2.oss.core.options.impl;

import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * A dummy persistence support for numbered options.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
public class NoopNumberedOptionsPersistenceSupportImpl implements NumberedOptionsPersistenceSupport {
    /**
     * a logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    @PostConstruct
    public void onClass() {
        LOG.info("Using a non-operational NumberedOptionsPersistenceSupport. Please reconfigure an alternative.");
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.options.api.AdditionalOptionsPersistenceSupport#findOptionByIdentifier(ds2.oss
     * .core.api.options.OptionIdentifier)
     */
    @Override
    public <V> OptionDto<Long, V> findOptionByIdentifier(OptionIdentifier<V> ident) {
        LOG.info("Using a non-operational method. Returning dummy value!");
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.options.api.AdditionalOptionsPersistenceSupport#setOptionStage(ds2.oss.core.
     * api.options.OptionIdentifier, ds2.oss.core.api.options.OptionStage)
     */
    @Override
    public <V> OptionDto<Long, V> setOptionStage(OptionIdentifier<V> ident, OptionStage newStage) {
        LOG.info("Using a non-operational method. Returning dummy value!");
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(OptionDto<Long, ?> t) {
        LOG.info("Using a non-operational method. Returning dummy value!");
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public OptionDto<Long, ?> getById(Long e) {
        LOG.info("Using a non-operational method. Returning dummy value!");
        return null;
    }
    
}
