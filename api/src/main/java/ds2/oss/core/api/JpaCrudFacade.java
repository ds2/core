package ds2.oss.core.api;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dstrauss on 04.07.17.
 */
public interface JpaCrudFacade<PRIMKEY, E extends Persistable<PRIMKEY>> extends PersistenceSupport<E, PRIMKEY> {
    List<E> getAll(int offset, int size);

    List<E> getAllByQuery(int offset, int size, TypedQuery<E> query);

}
