/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.options.ValueType;

/**
 * Provider for encrypting data.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
public interface OptionValueEncrypterProvider {
    <V> OptionValueEncrypter<V> getForValueType(ValueType t, Class<V> v);
}
