/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.dbtools.converters;

import ds2.oss.core.statics.Converts;
import ds2.oss.core.statics.Methods;

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
        if (attribute != null) {
            return attribute.getDisplayName(Locale.US);
        }
        return null;
    }

    @Override
    public Locale convertToEntityAttribute(String dbData) {
        Locale l = null;
        if (!Methods.isBlank(dbData)) {
            l = Converts.parseLocaleString(dbData);
        }
        return l;
    }
}
