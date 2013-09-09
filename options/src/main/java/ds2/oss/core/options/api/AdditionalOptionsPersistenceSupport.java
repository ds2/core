/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * The contract for an options persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <K>
 *            the primary key type
 */
public interface AdditionalOptionsPersistenceSupport<K> extends PersistenceSupport<OptionDto<K, ?>, K> {
    // nothing special to do
    /**
     * Finds an option with the given data.
     * 
     * @param ident
     *            the option identifier
     * @return the found option, or null
     */
    <V> OptionDto<K, V> findOptionByIdentifier(OptionIdentifier<V> ident);
}
