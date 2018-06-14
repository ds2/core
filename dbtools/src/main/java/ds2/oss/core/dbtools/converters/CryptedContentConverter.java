/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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

import ds2.oss.core.api.crypto.HashedResult;
import ds2.oss.core.api.dto.impl.HashedResultDto;
import ds2.oss.core.statics.Converts;
import ds2.oss.core.statics.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Converter
public class CryptedContentConverter implements AttributeConverter<HashedResult, String> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private String dataSeparator = ":";

    @Override
    public String convertToDatabaseColumn(HashedResult attribute) {
        LOG.debug("Hashed result to encode is: {}", attribute);
        if (attribute == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(200);
        sb.append(attribute.getAlgorithmName());
        sb.append(dataSeparator).append(attribute.getRounds());
        sb.append(dataSeparator).append(Base64.getEncoder().encode(attribute.getInitVector()));
        sb.append(dataSeparator).append(Base64.getEncoder().encode(attribute.getEncoded()));
        return null;
    }

    @Override
    public HashedResult convertToEntityAttribute(String dbData) {
        LOG.debug("DbData to convert is: {}", dbData);
        if (Methods.isBlank(dbData)) {
            return null;
        }
        HashedResultDto resultDto = new HashedResultDto();
        String[] splits = dbData.split(dataSeparator);
        if (splits.length < 4) {
            LOG.warn("DbData received is incompatible by this converter!");
            return null;
        }
        resultDto.setAlgorithmName(splits[0]);
        resultDto.setRounds(Converts.toInt(splits[1], -1));
        resultDto.setInitVector(Base64.getDecoder().decode(splits[2].getBytes(StandardCharsets.ISO_8859_1)));
        resultDto.setEncoded(Base64.getDecoder().decode(splits[3].getBytes(StandardCharsets.ISO_8859_1)));
        return resultDto;
    }
}
