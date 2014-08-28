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
package ds2.oss.core.options.impl;

import javax.enterprise.util.AnnotationLiteral;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * The value codec marker literal.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class ValueCodecMarkerLiteral extends AnnotationLiteral<ValueCodecMarker> implements ValueCodecMarker {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -4007105900167001507L;
    /**
     * The value type.
     */
    private ValueType value;
    
    /**
     * Inits the marker.
     * 
     * @param t
     *            the value type to scan for
     */
    public ValueCodecMarkerLiteral(final ValueType t) {
        value = t;
    }
    
    @Override
    public ValueType value() {
        return value;
    }
    
}
