/**
 * 
 */
package ds2.oss.core.options.impl.noop;

import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.options.api.NumberedOptionValuePersistenceSupport;

/**
 * Simple noop option value persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class NoopOptionValuePersistenceSupportImpl implements NumberedOptionValuePersistenceSupport {
    /**
     * a logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(OptionValueDto<Long, ?> t) {
        LOG.info("Using non-operational method. Please reconfigure injection!");
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public OptionValueDto<Long, ?> getById(Long e) {
        LOG.info("Using non-operational method. Please reconfigure injection!");
        return null;
    }
    
}
