/**
 * 
 */
package ds2.oss.core.options.internal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.options.ValueType;

/**
 * A value type converter.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Converter
public class ValueTypeConverter implements AttributeConverter<ValueType, Integer> {
    
    /*
     * (non-Javadoc)
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
     */
    @Override
    public Integer convertToDatabaseColumn(final ValueType attribute) {
        return Integer.valueOf(attribute.getNumericalValue());
    }
    
    /*
     * (non-Javadoc)
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
     */
    @Override
    public ValueType convertToEntityAttribute(final Integer dbData) {
        return ValueType.getById(dbData.intValue());
    }
    
}
