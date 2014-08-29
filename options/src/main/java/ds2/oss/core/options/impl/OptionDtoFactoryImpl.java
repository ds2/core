/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.options.impl;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.options.api.OptionFactory;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * The factory.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class OptionDtoFactoryImpl implements OptionFactory {
    
    @Override
    public <K, V> OptionDto<K, V> createOptionDto(final OptionIdentifier<V> ident, final K primKey, final V defaultVal) {
        final OptionDto<K, V> rc = new OptionDto<>(primKey);
        rc.setApplicationName(ident.getApplicationName());
        rc.setDefaultValue(defaultVal);
        rc.setEncrypted(ident.isEncrypted());
        rc.setOptionName(ident.getOptionName());
        rc.setValueType(ident.getValueType());
        rc.setStage(OptionStage.Online);
        return rc;
    }
    
}
