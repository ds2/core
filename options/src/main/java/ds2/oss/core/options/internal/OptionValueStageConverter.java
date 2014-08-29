/**
 * 
 */
package ds2.oss.core.options.internal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.options.OptionValueStage;

/**
 * The option value stage converter.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Converter
public class OptionValueStageConverter implements AttributeConverter<OptionValueStage, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(OptionValueStage attribute) {
        return attribute.getStageId();
    }
    
    @Override
    public OptionValueStage convertToEntityAttribute(Integer dbData) {
        return OptionValueStage.getById(dbData.intValue());
    }
    
}
