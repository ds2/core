/**
 * 
 */
package ds2.oss.core.base.impl.test;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.options.api.OptionsPersistenceSupport;
import ds2.oss.core.options.api.OptionsPersistenceSupport.StorageType;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * A dummy persistence store.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@OptionsPersistenceSupport(type = StorageType.NUMBERED)
public class DummyPersistenceStore implements PersistenceSupport<Option<Long, ?>, Long> {
    /**
     * Runtime primary key id.
     */
    private long id;
    
    @Override
    public void persist(final Option<Long, ?> t) {
        final OptionDto<Long, ?> o = (OptionDto<Long, ?>) t;
        o.setId(Long.valueOf(id++));
    }
    
    @Override
    public Option<Long, ?> getById(final Long e) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
