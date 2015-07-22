/**
 * 
 */
package ds2.oss.core.api;

/**
 * Dummy contract for a dto mapper.
 * 
 * @author dstrauss
 * @param <E>
 *            the entity type
 * @param <K>
 *            the primary key type
 * @param <D>
 *            the dto type
 *            
 */
public interface DtoMapper<E extends Persistable<K>, K, D> {
    /**
     * Converts the given entity into its dto representation.
     * 
     * @param e
     *            the entity
     * @return the dto
     */
    D convert(E e);
}
