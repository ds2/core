/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;

/**
 * Factory to create options. Implementations of this contract will return a dto object that matches
 * their persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionFactory {
    /**
     * Creates a dto that can be used to get stored and loaded via its persistence support.
     * 
     * @param ident
     *            the option identifier
     * @param primaryKey
     *            the primary key for this option. Set to null if the persistence layer will setup
     *            an id.
     * @param defaultVal
     *            the default value for the option
     * @param <K>
     *            the primary key type
     * @param <V>
     *            the value type
     * @return the option implementation
     */
    <K, V> Option<K, V> createOptionEntity(OptionIdentifier<V> ident, K primaryKey, V defaultVal);
}
