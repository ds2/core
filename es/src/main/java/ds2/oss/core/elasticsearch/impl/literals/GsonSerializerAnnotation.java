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
package ds2.oss.core.elasticsearch.impl.literals;

import javax.enterprise.util.AnnotationLiteral;

import ds2.oss.core.elasticsearch.api.annotations.GsonSerializer;

/**
 * The gson serializer annotation literal.
 * 
 * @author dstrauss
 * @version 0.2
 * 
 */
public class GsonSerializerAnnotation extends AnnotationLiteral<GsonSerializer> implements GsonSerializer {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 9212636188583637106L;
    /**
     * The class for this annotation.
     */
    private Class<?> c;
    
    /**
     * Inits the annotation.
     * 
     * @param c
     *            the class of this annotation
     * 
     */
    public GsonSerializerAnnotation(final Class<?> c) {
        super();
        this.c = c;
    }
    
    @Override
    public Class<?> value() {
        return c;
    }
    
}
