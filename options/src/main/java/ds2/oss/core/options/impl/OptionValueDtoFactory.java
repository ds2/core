/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.options.api.OptionValueFactory;

/**
 * The factory to generate option value DTOs.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class OptionValueDtoFactory implements OptionValueFactory {
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.options.api.OptionValueFactory#createOptionValueDto(ds2.oss.core.api.options
     * .OptionIdentifier, java.lang.Object, ds2.oss.core.api.options.OptionValueContext,
     * java.lang.Object)
     */
    @Override
    public <K, V> OptionValueDto<K, V> createOptionValueDto(OptionIdentifier<V> ident, K primaryKey,
        OptionValueContext ctx, V val) {
        OptionValueDto<K, V> rc = new OptionValueDto<K, V>();
        rc.setId(primaryKey);
        return rc;
    }
    
}
