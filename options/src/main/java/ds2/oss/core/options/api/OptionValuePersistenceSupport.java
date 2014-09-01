/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.dto.impl.OptionValueDto;

/**
 * Contract to persist option values.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <K>
 *            the key type for the dto
 *
 */
public interface OptionValuePersistenceSupport<K> extends PersistenceSupport<OptionValueDto<K, ?>, K> {
    
}
