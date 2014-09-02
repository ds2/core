/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.options.ForValueType;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.OptionValueEncrypter;
import ds2.oss.core.options.api.OptionValueEncrypterProvider;

/**
 * The enc provider.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
public class OptionValueEncrypterProviderImpl implements OptionValueEncrypterProvider {
    /**
     * The string encrypter.
     */
    @Inject
    @ForValueType(ValueType.STRING)
    private OptionValueEncrypter<String> stringEnc;
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.options.api.OptionValueEncrypterProvider#getForValueType(ds2.oss.core.api.options
     * .ValueType, java.lang.Class)
     */
    @Override
    public <V> OptionValueEncrypter<V> getForValueType(ValueType t, Class<V> v) {
        switch (t) {
            case STRING:
                return (OptionValueEncrypter<V>) stringEnc;
        }
        return null;
    }
    
}
