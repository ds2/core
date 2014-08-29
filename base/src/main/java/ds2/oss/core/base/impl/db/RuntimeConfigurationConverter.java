/**
 * 
 */
package ds2.oss.core.base.impl.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.environment.RuntimeConfiguration;

/**
 * To convert between runtimeConfig and db data.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Converter
public class RuntimeConfigurationConverter implements AttributeConverter<RuntimeConfiguration, String> {
    
    @Override
    public String convertToDatabaseColumn(RuntimeConfiguration attribute) {
        return attribute.getIdentifier();
    }
    
    @Override
    public RuntimeConfiguration convertToEntityAttribute(String dbData) {
        return RuntimeConfiguration.parseConfig(dbData);
    }
    
}
