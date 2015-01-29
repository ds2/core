/**
 * 
 */
package ds2.oss.core.codec.gson;

import javax.enterprise.util.AnnotationLiteral;

import ds2.oss.core.codec.gson.api.GsonSerializer;

/**
 * @author dstrauss
 *
 */
public class GsonSerializerAnnotationLiteral extends AnnotationLiteral<GsonSerializer> implements GsonSerializer {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1192047629402413795L;
    
    @Override
    public Class<?> value() {
        return null;
    }
    
}
