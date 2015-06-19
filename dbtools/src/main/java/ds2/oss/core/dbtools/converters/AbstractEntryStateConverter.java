package ds2.oss.core.dbtools.converters;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.EntryStates;

import javax.persistence.AttributeConverter;

/**
 * Created by dstrauss on 19.06.15.
 */
public abstract class AbstractEntryStateConverter<E extends EntryState> implements AttributeConverter<E, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getNumericalValue();
    }

    @Override
    public E convertToEntityAttribute(final Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return getStateById(dbData.intValue());
    }

    protected abstract E getStateById(int id);

}
