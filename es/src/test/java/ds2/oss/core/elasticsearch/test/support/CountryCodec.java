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
package ds2.oss.core.elasticsearch.test.support;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.api.annotations.EsCodec;
import ds2.oss.core.elasticsearch.impl.AbstractTypeCodec;
import ds2.oss.core.elasticsearch.test.dto.CountryDto;

import javax.enterprise.context.ApplicationScoped;

/**
 * The country codec.
 * 
 * @author dstrauss
 * @version 0.2
 */
@EsCodec(CountryDto.class)
@ApplicationScoped
public class CountryCodec extends AbstractTypeCodec<CountryDto> implements TypeCodec<CountryDto> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CountryCodec.class);
    
    @Override
    public String toJson(final CountryDto t) {
        try {
            final XContentBuilder builder =
                XContentFactory.jsonBuilder().startObject().field("name", t.getName()).field("isoCode", t.getIsoCode())
                    .endObject();
            return builder.string();
        } catch (final IOException e) {
            LOG.warn("Error when generating the JSON", e);
        }
        return null;
    }
    
    @Override
    public String getIndexTypeName() {
        return "country";
    }
    
    @Override
    public String getMapping() {
        return "{\"country\":{\"properties\":{\"name\":{\"type\":\"string\",\"index\":\"analyzed\"},\"isoCode\":{\"type\":\"string\",\"index\":\"analyzed\"}}}}\n";
    }
    
    @Override
    public CountryDto toDto(final Map<String, Object> fields) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
