package ds2.oss.core.dbtools.it;

import ds2.oss.core.api.EntryState;

/**
 * Created by dstrauss on 19.06.15.
 */
public interface MyEntityService {
    MyEntity create(String name, EntryState state);
    MyEntity getEntityById(long id);
}
