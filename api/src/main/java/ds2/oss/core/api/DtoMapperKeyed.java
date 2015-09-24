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
public interface DtoMapperKeyed<E extends Persistable<K>, K, D> extends DtoMapper<E, D> {
}
