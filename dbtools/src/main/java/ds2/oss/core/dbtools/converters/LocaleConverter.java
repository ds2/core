package ds2.oss.core.dbtools.converters;

import ds2.oss.core.statics.Converts;
import ds2.oss.core.statics.Methods;
import ds2.oss.core.statics.Tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;

/**
 * Created by dstrauss on 16.06.16.
 */
@Converter
public class LocaleConverter implements AttributeConverter<Locale, String> {
    @Override
    public String convertToDatabaseColumn(Locale attribute) {
        if(attribute!=null){
            return attribute.getDisplayName(Locale.US);
        }
        return null;
    }

    @Override
    public Locale convertToEntityAttribute(String dbData) {
        Locale l=null;
        if(!Methods.isBlank(dbData)){
            l= Converts.parseLocaleString(dbData);
        }
        return l;
    }
}
