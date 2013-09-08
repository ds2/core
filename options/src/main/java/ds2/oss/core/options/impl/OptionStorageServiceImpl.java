/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStorageService;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * The option storage impl.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the persistence type
 */
@ApplicationScoped
public abstract class OptionStorageServiceImpl<E> implements OptionStorageService<E> {
    /**
     * The option persistence support.
     */
    @Inject
    private PersistenceSupport<Option<E, ?>, E> optionPersistenceSvc;
    
    @Override
    public <V> Option<E, V> createOption(final OptionIdentifier<V> ident, final V val) {
        final OptionDto<E, V> option = new OptionDto<>();
        option.setApplicationName(ident.getApplicationName());
        option.setDefaultValue(val);
        option.setEncrypted(ident.isEncrypted());
        option.setOptionName(ident.getOptionName());
        option.setValueType(ident.getValueType());
        optionPersistenceSvc.persist(option);
        return option;
    }
}
