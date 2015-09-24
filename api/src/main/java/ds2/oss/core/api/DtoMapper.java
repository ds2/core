/**
 * 
 */
package ds2.oss.core.api;

import java.util.Collection;
import java.util.List;

/**
 * @author dstrauss
 * @param <A>
 *            the origin dto type
 * @param <B>
 *            the target dto type
 *            
 */
public interface DtoMapper<A, B> {
    /**
     * Converts the given entity into its dto representation.
     * 
     * @param a
     *            the entity
     * @return the dto
     */
    B convert(A a);
    
    /**
     * @param secureList
     * @return the list
     */
    List<B> convertToList(Collection<A> secureList);
}
