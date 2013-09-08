/**
 * 
 */
package ds2.oss.core.options.impl.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.ValueType;

/**
 * The option impl.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the primary key type
 * @param <V>
 *            the value type
 */
@XmlType(name = "optionType")
@XmlAccessorType(XmlAccessType.FIELD)
public class OptionDto<E, V> implements Option<E, V> {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 95352858832065847L;
    /**
     * The id for the persistence.
     */
    @NotNull
    private E id;
    /**
     * The stage of this option.
     */
    private OptionStage stage;
    /**
     * The modifier username.
     */
    @NotNull
    private String modifierName;
    /**
     * The default value.
     */
    private V defaultValue;
    /**
     * The modification date.
     */
    private Date modified;
    /**
     * The creation date.
     */
    @NotNull
    private Date created;
    /**
     * Flag for encrypted value.
     */
    private boolean encrypted;
    /**
     * The type of the value.
     */
    @NotNull
    private ValueType valueType;
    /**
     * The option name.
     */
    @NotNull
    @Min(3)
    @Max(30)
    private String optionName;
    /**
     * The application name.
     */
    @NotNull
    private String applicationName;
    
    /**
     * Inits the option with dummy default values.
     */
    public OptionDto() {
        // nothing special to do
        stage = OptionStage.Online;
        created = new Date();
        modified = created;
    }
    
    /**
     * Inits the option with a default id.
     * 
     * @param id1
     */
    public OptionDto(final E id1) {
        this.id = id1;
    }
    
    @Override
    public E getId() {
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
        return created;
    }
    
    @Override
    public Date getModified() {
        return modified;
    }
    
    @Override
    public V getDefaultValue() {
        return defaultValue;
    }
    
    @Override
    public String getModifierName() {
        return modifierName;
    }
    
    @Override
    public OptionStage getStage() {
        return stage;
    }
    
    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final E id) {
        this.id = id;
    }
    
    /**
     * Sets the stage for the option.
     * 
     * @param stage
     *            the stage to set
     */
    public void setStage(final OptionStage stage) {
        this.stage = stage;
    }
    
    /**
     * Sets the modifier name.
     * 
     * @param modifierName
     *            the modifierName to set
     */
    public void setModifierName(final String modifierName) {
        this.modifierName = modifierName;
    }
    
    /**
     * Sets the default value.
     * 
     * @param defaultValue
     *            the defaultValue to set
     */
    public void setDefaultValue(final V defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    /**
     * Sets the modification date.
     * 
     * @param modified
     *            the modified to set
     */
    public void setModified(final Date modified) {
        this.modified = modified;
    }
    
    /**
     * Sets the creation date.
     * 
     * @param created
     *            the created to set
     */
    public void setCreated(final Date created) {
        this.created = created;
    }
    
    /**
     * Sets the encryption flag.
     * 
     * @param encrypted
     *            the encrypted to set
     */
    public void setEncrypted(final boolean encrypted) {
        this.encrypted = encrypted;
    }
    
    /**
     * Sets the value type.
     * 
     * @param valueType
     *            the valueType to set
     */
    public void setValueType(final ValueType valueType) {
        this.valueType = valueType;
    }
    
    /**
     * Sets the option name.
     * 
     * @param optionName
     *            the optionName to set
     */
    public void setOptionName(final String optionName) {
        this.optionName = optionName;
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
    
}
