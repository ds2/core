/**
 * 
 */
package ds2.oss.core.options.internal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.options.OptionStage;

/**
 * An option stage converter.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Converter
public class OptionStageConverter implements AttributeConverter<OptionStage, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(final OptionStage attribute) {
        return Integer.valueOf(attribute.getNumericalValue());
    }
    
    @Override
    public OptionStage convertToEntityAttribute(final Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return OptionStage.getById(dbData.intValue());
    }
    
}
