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

import java.lang.annotation.Annotation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueTypeParser;
import ds2.oss.core.options.impl.dto.OptionDto;
import ds2.oss.core.options.impl.dto.OptionEntity;

/**
 * The value type parser impl.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class ValueTypeParserImpl implements ValueTypeParser {
    /**
     * The codecs.
     */
    @Any
    @Inject
    private Instance<ValueCodec<?>> codecs;
    
    @Override
    public <V> V parseValue(final ValueType t, final Class<V> targetClass, final Object thisVal, final V onNull) {
        if (thisVal == null) {
            return onNull;
        }
        V rc = null;
        final Annotation a = new ValueCodecMarkerLiteral(t);
        final ValueCodec<V> codec = (ValueCodec<V>) codecs.select(a).get();
        rc = codec.toValue((String) thisVal);
        return rc;
    }
    
    @Override
    public String toString(final ValueType valueType, final Object val) {
        String rc = null;
        final Annotation a = new ValueCodecMarkerLiteral(valueType);
        final ValueCodec<Object> codec = (ValueCodec<Object>) codecs.select(a).get();
        rc = codec.toString(val);
        return rc;
    }
    
    @Override
    public <V> OptionDto<Long, V> toDto(final OptionEntity e, final OptionIdentifier<V> ident) {
        if (e == null) {
            return null;
        }
        final OptionDto<Long, V> rc = new OptionDto<>(e.getId());
        rc.setApplicationName(e.getApplicationName());
        rc.setCreated(e.getCreated());
        rc.setEncrypted(e.isEncrypted());
        rc.setModified(e.getModified());
        rc.setModifierName(e.getModifierName());
        rc.setOptionName(e.getOptionName());
        rc.setStage(e.getStage());
        rc.setValueType(e.getValueType());
        final Annotation a = new ValueCodecMarkerLiteral(ident.getValueType());
        final ValueCodec<V> codec = (ValueCodec<V>) codecs.select(a).get();
        rc.setDefaultValue(codec.toValue((String) e.getDefaultValue()));
        return rc;
    }
    
}
