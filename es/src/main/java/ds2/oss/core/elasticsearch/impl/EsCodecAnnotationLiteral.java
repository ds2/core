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
