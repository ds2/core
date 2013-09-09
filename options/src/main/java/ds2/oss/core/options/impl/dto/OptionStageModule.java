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

import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.base.impl.EnumModule;

/**
 * The option stage module.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Embeddable
public class OptionStageModule extends EnumModule<OptionStage> {
    private int value;
    
    /**
     * Inits the module.
     */
    public OptionStageModule() {
        super(OptionStage.class);
    }
    
    @Override
    public OptionStage getValue() {
        return super.getValue();
    }
    
    @Override
    public void setValue(final OptionStage e) {
        super.setValue(e);
    }
}
