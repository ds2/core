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
package ds2.oss.core.options.impl.dto;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.CreatedModifiedAwareModule;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.internal.OptionStageConverter;
import ds2.oss.core.options.internal.ValueTypeConverter;

/**
 * A database option.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Entity(name = "coreOption")
@Table(name = "core_options", uniqueConstraints = { @UniqueConstraint(columnNames = { "app_name", "name" }) })
@TableGenerator(
    name = "tableGen1",
    initialValue = 1,
    pkColumnName = "table_name",
    pkColumnValue = "core_options",
    table = "core_id",
    valueColumnName = "next_id",
    allocationSize = 1)
@SequenceGenerator(initialValue = 1, name = "seqGen1", sequenceName = "SEQ_CORE_OPTIONS", allocationSize = 1)
@NamedQueries({ @NamedQuery(
    name = NumberedOptionsPersistenceSupport.QUERY_FINDOPTIONBYIDENTIFIER,
    query = "select o from coreOption o where o.optionName = :optionName and o.applicationName = :appName") })
@Access(AccessType.FIELD)
public class OptionEntity implements Option<Long, Object> {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8598846864284031760L;
    /**
     * The id of the entry.
     */
    @Id
    @GeneratedValue(generator = "tableGen1", strategy = GenerationType.TABLE)
    private Long id;
    /**
     * The application name.
     */
    @Column(name = "app_name")
    private String applicationName;
    /**
     * The CMA module.
     */
    @Embedded
    private CreatedModifiedAwareModule cma;
    /**
     * The option name.
     */
    @Column(name = "name", nullable = false)
    private String optionName;
    /**
     * The value type.
     */
    @Column(name = "value_type", nullable = false)
    @Convert(converter = ValueTypeConverter.class)
    private ValueType valueType;
    /**
     * The encrypted flag.
     */
    @Column(name = "encrypted", nullable = false)
    private boolean encrypted;
    /**
     * The default value.
     */
    @Column(name = "value", length = 3000)
    private String defaultValue;
    /**
     * The stage value.
     */
    @Column(name = "stage", nullable = false)
    @Convert(converter = OptionStageConverter.class)
    private OptionStage stageVal;
    /**
     * The modifier username.
     */
    @Column(name = "modified_by")
    private String modifierName;
    
    /**
     * Inits the entity.
     */
    public OptionEntity() {
        cma = new CreatedModifiedAwareModule();
    }
    
    @Override
    public Long getId() {
        return id;
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
        return encrypted;
    }
    
    @Override
    public Date getCreated() {
        return cma.getCreated();
    }
    
    @Override
    public Date getModified() {
        return cma.getModified();
    }
    
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
    
    @Override
    public String getModifierName() {
        return modifierName;
    }
    
    @Override
    public OptionStage getStage() {
        return stageVal;
    }
    
    /**
     * Sets the stage value.
     * 
     * @param s
     *            the stage value
     */
    public void setStage(final OptionStage s) {
        stageVal = s;
    }
    
    /**
     * Sets the application name.
     * 
     * @param applicationName
     *            the applicationName to set
     */
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }
    
    /**
     * Sets the option name.
     * 
     * @param optionName1
     *            the optionName to set
     */
    public void setOptionName(final String optionName1) {
        optionName = optionName1;
    }
    
    /**
     * Sets the value type.
     * 
     * @param valueType1
     *            the valueType to set
     */
    public void setValueType(final ValueType valueType1) {
        valueType = valueType1;
    }
    
    /**
     * Sets the encrypted flag.
     * 
     * @param encrypted1
     *            the encrypted to set
     */
    public void setEncrypted(final boolean encrypted1) {
        encrypted = encrypted1;
    }
    
    /**
     * Sets the default value.
     * 
     * @param defaultValue
     *            the defaultValue to set
     */
    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    /**
     * Sets the modifier.
     * 
     * @param modifierName
     *            the modifierName to set
     */
    public void setModifierName(final String modifierName) {
        this.modifierName = modifierName;
    }
    
    /**
     * Sets another creation date.
     * 
     * @param created
     *            the creation date.
     */
    public void setCreated(final Date created) {
        cma.setCreated(created);
    }
    
    /**
     * Sets another modified date.
     * 
     * @param modified
     *            the modified date
     */
    public void setModified(final Date modified) {
        cma.setModified(modified);
    }
    
    /**
     * Updates the modified date.
     */
    public void touchModified() {
        cma.touchModified();
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("OptionEntity (id=");
        builder.append(id);
        builder.append(", applicationName=");
        builder.append(applicationName);
        builder.append(", cma=");
        builder.append(cma);
        builder.append(", optionName=");
        builder.append(optionName);
        builder.append(", valueType=");
        builder.append(valueType);
        builder.append(", encrypted=");
        builder.append(encrypted);
        builder.append(", defaultValue=");
        builder.append(defaultValue);
        builder.append(", stageVal=");
        builder.append(stageVal);
        builder.append(", modifierName=");
        builder.append(modifierName);
        builder.append(")");
        return builder.toString();
    }
    
}
