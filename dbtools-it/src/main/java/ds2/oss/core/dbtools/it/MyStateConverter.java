package ds2.oss.core.dbtools.it;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.dbtools.converters.AbstractEntryStateConverter;

import javax.persistence.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dstrauss on 19.06.15.
 */
@Converter
public class MyStateConverter extends AbstractEntryStateConverter<StateEntity> {
    @PersistenceContext(unitName = "octest")
    private EntityManager em;

    @Override
    protected StateEntity getStateById(int id) {
        return em.find(StateEntity.class, id);
    }
}
