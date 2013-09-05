/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStorageService;

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
    @Override
    public <V> Option<E, V> createOption(final OptionIdentifier<V> ident, final V val) {
        // TODO Auto-generated method stub
        return null;
    }
}
