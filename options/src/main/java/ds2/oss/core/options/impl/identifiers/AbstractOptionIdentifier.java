/*
 * Copyright 2012-2015 Dirk Strauss
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
package ds2.oss.core.options.impl.identifiers;

import javax.validation.constraints.NotNull;

import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.ValueType;

/**
 * An abstract option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the type of the option
 */
public abstract class AbstractOptionIdentifier<V> implements OptionIdentifier<V> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5342494707991378995L;
    /**
     * The option name.
     */
    @NotNull
    private String optionName;
    /**
     * The application name.
     */
    @NotNull
    private String applicationName;
    /**
     * the value type.
     */
    @NotNull
    private ValueType valueType;
    /**
     * A description of the option.
     */
    private String description;
    
    /**
     * Inits the option identifier.
     * 
     * @param appName
     *            the application name
     * 
     * @param optName
     *            the option name
     * @param typeClass
     *            the class type
     * 
     */
    public AbstractOptionIdentifier(final String appName, final String optName, final ValueType typeClass) {
        applicationName = appName;
        optionName = optName;
        valueType = typeClass;
    }
    
    /**
     * Sets the option name.
     * 
     * @param optionName
     *            the optionName to set
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
    
    /**
     * Sets the application name.
     * 
     * @param applicationName
     *            the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    /**
     * Sets the value type.
     * 
     * @param valueType
     *            the valueType to set
     */
    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }
    
    /**
     * Sets a description for this option identifier.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String getApplicationName() {
        return applicationName;
    }
    
    @Override
    public String getOptionName() {
        return optionName;
    }
    
    @Override
    public ValueType getValueType() {
        return valueType;
    }
    
    @Override
    public boolean isEncrypted() {
        return false;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}
