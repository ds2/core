/**
 * 
 */
package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.options.api.OptionFactory;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * The factory.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class OptionFactoryImpl implements OptionFactory {
    
    @Override
    public <K, V> Option<K, V> createOptionEntity(final OptionIdentifier<V> ident, final K primKey, final V defaultVal) {
        final OptionDto<K, V> rc = new OptionDto<>(primKey);
        rc.setApplicationName(ident.getApplicationName());
        rc.setDefaultValue(defaultVal);
        rc.setEncrypted(ident.isEncrypted());
        rc.setOptionName(ident.getOptionName());
        rc.setValueType(ident.getValueType());
        return rc;
    }
    
}
