/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueTypeParser;

/**
 * The value type parser impl.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class ValueTypeParserImpl implements ValueTypeParser {
    
    @Override
    public <V> V parseValue(@NotNull final ValueType t, final Class<V> targetClass, final Object thisVal, final V onNull) {
        if (thisVal == null) {
            return onNull;
        }
        V rc = null;
        switch (t) {
            case STRING:
                rc = targetClass.cast(thisVal);
                break;
            case BOOLEAN:
                if (thisVal instanceof String) {
                    rc = targetClass.cast(Boolean.parseBoolean(thisVal.toString()));
                }
                break;
            default:
                rc = targetClass.cast(thisVal);
                break;
        }
        return rc;
    }
    
}
