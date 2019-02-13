/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.options.internal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ds2.oss.core.api.options.OptionValueStage;

/**
 * The option value stage converter.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Converter
public class OptionValueStageConverter implements AttributeConverter<OptionValueStage, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(OptionValueStage attribute) {
        return attribute.getStageId();
    }
    
    @Override
    public OptionValueStage convertToEntityAttribute(Integer dbData) {
        return OptionValueStage.getById(dbData.intValue());
    }
    
}
