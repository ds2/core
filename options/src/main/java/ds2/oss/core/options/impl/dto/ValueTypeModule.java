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
package ds2.oss.core.options.impl.dto;

import javax.persistence.Embeddable;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.EnumModule;

/**
 * The value type module.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Embeddable
public class ValueTypeModule extends EnumModule<ValueType> {
    private int value;
    
    public ValueTypeModule() {
        super(ValueType.class);
    }
    
    @Override
    public void setValue(final ValueType e) {
        super.setValue(e);
    }
    
    @Override
    public ValueType getValue() {
        return super.getValue();
    }
    
}
