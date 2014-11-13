/**
 * 
 */
package ds2.oss.core.dbtools.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.EntryStates;

/**
 * @author dstrauss
 *
 */
@Converter
public class EntryStatesConverter implements AttributeConverter<EntryStates, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(EntryStates attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getNumericalValue();
    }
    
    @Override
    public EntryStates convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return EntryStates.getById(dbData.intValue());
    }
    
}
