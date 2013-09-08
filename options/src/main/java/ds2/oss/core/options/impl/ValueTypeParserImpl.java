/**
 * 
 */
package ds2.oss.core.options.impl;

import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
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
    
    @Override
    public String toString(@NotNull final ValueType valueType, final Object val) {
        String rc = null;
        switch (valueType) {
            case STRING:
                if (val != null) {
                    rc = val.toString();
                }
                break;
            default:
                LOG.error("Unknown value type: {}, cannot parse to string!", valueType);
                break;
        }
        return rc;
    }
    
}
