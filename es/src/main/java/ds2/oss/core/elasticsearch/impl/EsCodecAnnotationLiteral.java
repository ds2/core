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
package ds2.oss.core.elasticsearch.impl;

import ds2.oss.core.elasticsearch.api.annotations.EsCodec;

import javax.enterprise.util.AnnotationLiteral;


/**
 * The ES Codec literal.
 * 
 * @version 0.2
 * @author dstrauss
 */
public class EsCodecAnnotationLiteral extends AnnotationLiteral<EsCodec> implements EsCodec {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -3513938617407447060L;
    /**
     * The class.
     */
    private final Class<?> c;
    
    /**
     * Inits the literal.
     * 
     * @param c
     *            the class to wrap. Usually a dto.
     */
    public EsCodecAnnotationLiteral(final Class<?> c) {
        this.c = c;
    }
    
    /**
     * The value to return.
     * 
     * @return the class value
     */
    @Override
    public Class<?> value() {
        return c;
    }
}
