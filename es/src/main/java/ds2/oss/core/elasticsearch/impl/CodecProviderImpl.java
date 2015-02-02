/*
 * Copyright 2012-2015 Dirk Strauss
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
package ds2.oss.core.elasticsearch.impl;

import java.lang.annotation.Annotation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.CodecProvider;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.impl.literals.EsCodecAnnotationLiteral;

/**
 * A provider for any found ES codec.
 * 
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
public class CodecProviderImpl implements CodecProvider {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CodecProviderImpl.class);
    /**
     * The found codecs.
     */
    @Inject
    @Any
    private Instance<TypeCodec<?>> foundCodecs;
    
    /**
     * Actions to perform after class init.
     */
    @PostConstruct
    public void onClass() {
        if (foundCodecs.isUnsatisfied()) {
            LOG.warn("found codecs are unsatisfied!");
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeCodec<T> findFor(final Class<T> c) {
        if (c == null) {
            return null;
        }
        
        TypeCodec<?> rc = null;
        final Annotation a = new EsCodecAnnotationLiteral(c);
        LOG.debug("Annotation of codec should be {}", a);
        try {
            rc = foundCodecs.select(a).get();
        } catch (final RuntimeException e) {
            LOG.warn("Error when looking up an instance of a codec!", e);
        }
        if (rc == null) {
            for (TypeCodec<?> codec : foundCodecs) {
                LOG.debug("Having codec {}", codec);
            }
        }
        LOG.debug("rc will be {}", rc);
        return (TypeCodec<T>) rc;
    }
    
    @Override
    public int getInstanceCount() {
        return 1;
    }
    
}
