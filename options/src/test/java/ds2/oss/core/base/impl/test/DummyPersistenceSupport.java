/**
 * 
 */
package ds2.oss.core.base.impl.test;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * Dummy persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
public class DummyPersistenceSupport implements NumberedOptionsPersistenceSupport {
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.options.api.AdditionalOptionsPersistenceSupport#findOptionByIdentifier(ds2.oss
     * .core.api.options.OptionIdentifier)
     */
    @Override
    public <V> OptionDto<Long, V> findOptionByIdentifier(OptionIdentifier<V> ident) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(OptionDto<Long, ?> t) {
        // TODO Auto-generated method stub
        
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public OptionDto<Long, ?> getById(Long e) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
