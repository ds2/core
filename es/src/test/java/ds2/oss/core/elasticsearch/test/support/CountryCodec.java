/*
 * Copyright 2012-2013 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *
 */
package ds2.oss.core.elasticsearch.test.support;

import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.test.dto.CountryDto;

import java.util.Map;

/**
 * @author  dstrauss
 */
public class CountryCodec implements TypeCodec<CountryDto> {

    /**
     */
    public CountryCodec() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toJson(final CountryDto t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CountryDto toDto(final Map<String, Object> o) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getIndexTypeName() {
        return "country";
    }

    @Override
    public String getIndexName() {
        return null;
    }

    @Override
    public String getMapping() {
        return null;
    }
}
