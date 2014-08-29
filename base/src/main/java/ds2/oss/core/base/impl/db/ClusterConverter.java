package ds2.oss.core.base.impl.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.ClusterDto;

/**
 * To convert between cluster contract and db data.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Converter
public class ClusterConverter implements AttributeConverter<Cluster, String> {
    
    @Override
    public String convertToDatabaseColumn(Cluster attribute) {
        return "" + attribute.getClusterName();
    }
    
    @Override
    public Cluster convertToEntityAttribute(String dbData) {
        return new ClusterDto(dbData);
    }
    
}
