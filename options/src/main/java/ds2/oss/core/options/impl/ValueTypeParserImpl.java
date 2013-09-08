/*
 * Copyright 2012-2013 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package ds2.oss.core.options.impl;

import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;

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
    public <V> V parseValue(final ValueType t, final Class<V> targetClass, final Object thisVal, final V onNull) {
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
    public String toString(final ValueType valueType, final Object val) {
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
