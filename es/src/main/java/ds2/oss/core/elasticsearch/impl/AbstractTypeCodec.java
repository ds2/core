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
package ds2.oss.core.elasticsearch.impl;

import javax.inject.Inject;

import ds2.oss.core.api.IoService;
import ds2.oss.core.elasticsearch.api.GsonCodec;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.api.annotations.TypeMapping;

/**
 * An abstract class defining some common methods for type codecs.
 * 
 * @author dstrauss
 * @version 0.2
 * @param <T>
 *            the type of the dto
 * 
 */
public abstract class AbstractTypeCodec<T> extends AbstractCodecBase implements TypeCodec<T> {
    /**
     * The IO service.
     */
    @Inject
    protected IoService ioSvc;
    /**
     * The dto base class represented by this codec.
     */
    private Class<T> baseClass;
    /**
     * The gson codec.
     */
    @Inject
    private GsonCodec gson;
    
    /**
     * Constructs an empty codec base.
     */
    protected AbstractTypeCodec() {
        // dummy constructor
    }
    
    /**
     * Inits the codec with a given dto base class.
     * 
     * @param base
     *            the dto base class to use.
     */
    protected AbstractTypeCodec(final Class<T> base) {
        this();
        baseClass = base;
    }
    
    /**
     * Performs a load operation for the given type to return the known mapping.
     * 
     * @param c
     *            the class of the type
     * @return the mapping, usually as json, or null if an error occurred
     */
    protected String loadMappingFromJson(final Class<T> c) {
        final StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(c.getPackage().getName().replaceAll("\\.", "/"));
        sb.append("/");
        sb.append(c.getSimpleName()).append("-elasticsearch.mapping.json");
        final String rc = ioSvc.loadResource(sb.toString());
        return rc;
    }
    
    @Override
    public String getMapping() {
        String rc = null;
        if (baseClass != null) {
            rc = this.loadMappingFromJson(baseClass);
        }
        if (rc == null) {
            throw new UnsupportedOperationException("No mapping data have been defined!");
        }
        return rc;
    }
    
    @Override
    public boolean matches(final Class<?> c) {
        if (baseClass != null) {
            return baseClass.isAssignableFrom(c);
        }
        return false;
    }
    
    @Override
    public T toDto(final String jsonContent) {
        if (baseClass != null) {
            return gson.decode(baseClass, jsonContent);
        }
        throw new UnsupportedOperationException("Cannot decode the given json string!");
    }
    
    @Override
    public String toJson(final T t) {
        return gson.encode(t);
    }
    
    @Override
    public String getIndexTypeName() {
        final TypeMapping tm = baseClass.getAnnotation(TypeMapping.class);
        if (tm != null) {
            return tm.value();
        }
        throw new UnsupportedOperationException("Cannot determine the index type name!");
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.elasticsearch.api.TypeCodec#refreshOnIndexing()
     */
    @Override
    public boolean refreshOnIndexing() {
        return false;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.elasticsearch.api.TypeCodec#replicateOnIndexing()
     */
    @Override
    public boolean replicateOnIndexing() {
        return false;
    }
}
