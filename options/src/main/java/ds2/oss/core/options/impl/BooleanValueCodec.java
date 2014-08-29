/*
 * Copyright 2012-2014 Dirk Strauss
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

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * A boolean codec.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ValueCodecMarker(ValueType.BOOLEAN)
public class BooleanValueCodec implements ValueCodec<Boolean> {
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.options.api.ValueCodec#toString(java.lang.Object)
     */
    @Override
    public String toString(final Boolean v) {
        if (v == null) {
            return null;
        }
        return v.toString();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.options.api.ValueCodec#toValue(java.lang.String)
     */
    @Override
    public Boolean toValue(final String s) {
        if (s == null) {
            return null;
        }
        return Boolean.valueOf(s);
    }
    
}
