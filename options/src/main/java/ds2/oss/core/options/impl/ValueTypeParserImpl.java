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
package ds2.oss.core.options.impl;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.OptionValueEncrypter;
import ds2.oss.core.options.api.OptionValueEncrypterProvider;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueTypeParser;
import ds2.oss.core.options.impl.entities.OptionEntity;
import ds2.oss.core.options.impl.entities.OptionValueEntity;

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
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The codecs.
     */
    @Any
    @Inject
    private Instance<ValueCodec<?>> codecs;
    @Inject
    private OptionValueEncrypterProvider encProvider;
    
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
        LOG.debug("Entity to convert is {}", e);
        final OptionDto<Long, V> rc = new OptionDto<>(e.getId());
        rc.setApplicationName(e.getApplicationName());
        rc.setCreated(e.getCreated());
        rc.setEncrypted(e.isEncrypted());
        rc.setModified(e.getModified());
        rc.setModifierName(e.getModifierName());
        rc.setOptionName(e.getOptionName());
        rc.setStage(e.getStage());
        rc.setValueType(e.getValueType());
        rc.setDescription(e.getDescription());
        rc.setEncoded(e.getEncoded());
        rc.setInitVector(e.getInitVector());
        final Annotation a = new ValueCodecMarkerLiteral(ident.getValueType());
        final ValueCodec<V> codec = (ValueCodec<V>) codecs.select(a).get();
        rc.setDefaultValue(codec.toValue((String) e.getDefaultValue()));
        return rc;
    }
    
    @Override
    public <V> OptionValueDto<Long, V> toDto(final OptionValueEntity e, final Class<V> valueClass) {
        if (e == null) {
            return null;
        }
        final OptionValueDto<Long, V> rc = new OptionValueDto<Long, V>();
        rc.setApproverName(e.getApproverName());
        rc.setAuthorName(e.getAuthorName());
        rc.setCluster(e.getCluster());
        rc.setConfiguration(e.getConfiguration());
        rc.setCreated(e.getCreated());
        rc.setEncoded(e.getEncoded());
        rc.setId(e.getId());
        rc.setInitVector(e.getInitVector());
        rc.setModified(e.getModified());
        rc.setOptionReference(e.getOptionReference());
        rc.setRequestedDomain(e.getRequestedDomain());
        rc.setServer(e.getServer());
        rc.setStage(e.getStage());
        rc.setValidFrom(e.getValidFrom());
        rc.setValidTo(e.getValidTo());
        rc.setValue(parseValue(e.getValueType(), valueClass, e.getValue(), null));
        rc.setValueType(e.getValueType());
        rc.setEncrypted(e.isEncrypted());
        if (e.isEncrypted()) {
            // decrypt it
            OptionValueEncrypter<V> encrypter = encProvider.getForValueType(rc.getValueType());
            rc.setUnencryptedValue(encrypter.decrypt(rc));
        }
        return rc;
    }
    
}
