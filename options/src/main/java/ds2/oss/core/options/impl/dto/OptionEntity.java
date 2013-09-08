/**
 * 
 */
package ds2.oss.core.options.impl.dto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.CreatedModifiedAwareModule;
import ds2.oss.core.base.impl.EnumModule;

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
    pkColumnValue = "next_id",
    table = "core_id",
    valueColumnName = "core_options",
    allocationSize = 1)
@SequenceGenerator(initialValue = 1, name = "seqGen1", sequenceName = "SEQ_CORE_OPTIONS", allocationSize = 1)
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
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "value", column = @Column(name = "value_type")) })
    private EnumModule<ValueType> valueType;
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
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "value", column = @Column(name = "stage")) })
    private EnumModule<OptionStage> stageVal;
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
        stageVal = new EnumModule<>(OptionStage.class);
        valueType = new EnumModule<>(ValueType.class);
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
        return valueType.getValue();
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
        return stageVal.getValue();
    }
    
    /**
     * Sets the stage value.
     * 
     * @param s
     *            the stage value
     */
    public void setStage(final OptionStage s) {
        stageVal.setValue(s);
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
        valueType.setValue(valueType1);
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
    
}
