/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

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
