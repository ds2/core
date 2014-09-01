/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValueContext;

/**
 * A contract to create an option value dto.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionValueFactory {
    /**
     * Creates an option value dto.
     * 
     * @param ident
     *            the option identifier
     * @param primaryKey
     *            the primary key. Leave to null to address creation to the persistence layer.
     * @param ctx
     *            the option value context
     * @param val
     *            the option value
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return the dto
     */
    <K, V> OptionValueDto<K, V> createOptionValueDto(OptionIdentifier<V> ident, K primaryKey, OptionValueContext ctx,
        V val);
}
