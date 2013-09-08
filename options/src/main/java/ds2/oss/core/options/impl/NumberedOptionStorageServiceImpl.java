/**
 * 
 */
package ds2.oss.core.options.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.options.api.OptionsPersistenceSupport;
import ds2.oss.core.options.api.OptionsPersistenceSupport.StorageType;

/**
 * The implementation for numbered options.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Alternative
@ApplicationScoped
public class NumberedOptionStorageServiceImpl extends AbstractOptionStorageServiceImpl<Long>
    implements
    NumberedOptionStorageService {
    /**
     * The persistence support to use for options..
     */
    @Inject
    @OptionsPersistenceSupport(type = StorageType.NUMBERED)
    private PersistenceSupport<Option<Long, ?>, Long> numberedPersistenceSupport;
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.api.options.OptionStorageService#getOptionByIdentifier(ds2.oss.core.api.options
     * .OptionIdentifier)
     */
    @Override
    public <V> Option<Long, V> getOptionByIdentifier(final OptionIdentifier<V> ident) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.api.options.OptionStorageService#createOptionValue(ds2.oss.core.api.options.
     * OptionIdentifier, ds2.oss.core.api.options.OptionValueContext, java.util.Date,
     * java.lang.Object)
     */
    @Override
    public <V> OptionValue<Long, V> createOptionValue(final OptionIdentifier<V> optionIdent,
        final OptionValueContext ctx, final Date scheduleDate, final V value) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.api.options.OptionStorageService#findBestOptionValueByContext(ds2.oss.core.api
     * .options.OptionIdentifier, ds2.oss.core.api.options.OptionValueContext)
     */
    @Override
    public <V> OptionValue<Long, V> findBestOptionValueByContext(final OptionIdentifier<V> ident,
        final OptionValueContext ctx) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#getAllOptions(java.lang.String)
     */
    @Override
    public List<Option<Long, ?>> getAllOptions(final String appName) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    protected PersistenceSupport<Option<Long, ?>, Long> getDataStore() {
        return numberedPersistenceSupport;
    }
    
}
