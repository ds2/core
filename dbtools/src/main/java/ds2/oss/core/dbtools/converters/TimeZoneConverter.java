package ds2.oss.core.dbtools.converters;

import ds2.oss.core.statics.Methods;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.TimeZone;

/**
 * Created by dstrauss on 16.06.16.
 */
@Converter
public class TimeZoneConverter implements AttributeConverter<TimeZone, String> {
    @Override
    public String convertToDatabaseColumn(TimeZone attribute) {
        if (attribute != null) {
            return attribute.getID();
        }
        return null;
    }

    @Override
    public TimeZone convertToEntityAttribute(String dbData) {
        if (!Methods.isBlank(dbData)) {
            return TimeZone.getTimeZone(dbData);
        }
        return null;
    }
}
