/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.options.ValueType;

/**
 * A value parser.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface ValueTypeParser {
    /**
     * Parses a given object into the target type.
     * 
     * @param t
     *            the target type
     * @param targetClass
     *            the target class
     * @param thisVal
     *            the object to parse
     * @param onNull TODO
     * @param <V>
     *            the target value
     * @return the value
     */
    <V> V parseValue(ValueType t, Class<V> targetClass, Object thisVal, V onNull);
}
