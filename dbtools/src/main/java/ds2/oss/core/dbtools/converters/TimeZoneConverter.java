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
