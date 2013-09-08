/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.inject.Inject;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStorageService;
import ds2.oss.core.options.api.OptionFactory;

/**
 * The option storage impl.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the persistence type
 */
public abstract class AbstractOptionStorageServiceImpl<E> implements OptionStorageService<E> {
    /**
     * The option factory.
     */
    @Inject
    private OptionFactory optionFac;
    
    @Override
    public <V> Option<E, V> createOption(final OptionIdentifier<V> ident, final V val) {
        final Option<E, V> option = optionFac.createOptionEntity(ident, null, val);
        getDataStore().persist(option);
        return option;
    }
    
    /**
     * Returns the datastore to use to store and load options.
     * 
     * @return the persistence support for dealing with options.
     */
    protected abstract PersistenceSupport<Option<E, ?>, E> getDataStore();
}
