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
package ds2.oss.core.api.dto.impl;

import ds2.oss.core.api.EditableCreatedModifiedAware;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.ValueType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * The option impl.
 *
 * @param <E> the primary key type
 * @param <V> the value type
 * @author dstrauss
 * @version 0.3
 */
@XmlType(name = "optionType")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class OptionDto<E, V> extends IvEncodedContentDto implements Option<E, V>, EditableCreatedModifiedAware {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 95352858832065847L;
    /**
     * The application name.
     */
    @NotNull
    @Size(min = 1, max = 30)
    private String applicationName;
    /**
     * The creation date.
     */
    private LocalDateTime created;
    /**
     * The decrypted value.
     */
    @XmlTransient
    private transient V decryptedValue;
    /**
     * The default value.
     */
    private V defaultValue;
    /**
     * The description.
     */
    private String description;
    /**
     * Flag for encrypted value.
     */
    private boolean encrypted;
    /**
     * The id for the persistence.
     */
    private E id;
    /**
     * The modification date.
     */
    private LocalDateTime modified;
    /**
     * The modifier username.
     */
    private String modifiedBy;
    @NotNull
    private String createdBy;
    /**
     * The option name.
     */
    @NotNull
    @Size(min = 3, max = 30)
    private String optionName;
    /**
     * The stage of this option.
     */
    private OptionStage stage;
    /**
     * The type of the value.
     */
    @NotNull
    private ValueType valueType;

    /**
     * Inits the option with dummy default values.
     */
    public OptionDto() {
        // nothing special to do
        stage = OptionStage.Online;
        created = LocalDateTime.now();
        modified = created;
    }

    /**
     * Inits the option with a default id.
     *
     * @param id1 the primary key of this option
     */
    public OptionDto(final E id1) {
        this();
        this.id = id1;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OptionDto other = (OptionDto) obj;
        if (applicationName == null) {
            if (other.applicationName != null) {
                return false;
            }
        } else if (!applicationName.equals(other.applicationName)) {
            return false;
        }
        if (created == null) {
            if (other.created != null) {
                return false;
            }
        } else if (!created.equals(other.created)) {
            return false;
        }
        if (defaultValue == null) {
            if (other.defaultValue != null) {
                return false;
            }
        } else if (!defaultValue.equals(other.defaultValue)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (encrypted != other.encrypted) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (modified == null) {
            if (other.modified != null) {
                return false;
            }
        } else if (!modified.equals(other.modified)) {
            return false;
        }
        if (modifiedBy == null) {
            if (other.modifiedBy != null) {
                return false;
            }
        } else if (!modifiedBy.equals(other.modifiedBy)) {
            return false;
        }
        if (optionName == null) {
            if (other.optionName != null) {
                return false;
            }
        } else if (!optionName.equals(other.optionName)) {
            return false;
        }
        if (stage != other.stage) {
            return false;
        }
        if (valueType != other.valueType) {
            return false;
        }
        return true;
    }


    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (applicationName == null ? 0 : applicationName.hashCode());
        result = prime * result + (created == null ? 0 : created.hashCode());
        result = prime * result + (defaultValue == null ? 0 : defaultValue.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (encrypted ? 1231 : 1237);
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (modified == null ? 0 : modified.hashCode());
        result = prime * result + (modifiedBy == null ? 0 : modifiedBy.hashCode());
        result = prime * result + (optionName == null ? 0 : optionName.hashCode());
        result = prime * result + (stage == null ? 0 : stage.hashCode());
        result = prime * result + (valueType == null ? 0 : valueType.hashCode());
        return result;
    }


    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OptionDto (id=");
        builder.append(id);
        builder.append(", stage=");
        builder.append(stage);
        builder.append(", modifiedBy=");
        builder.append(modifiedBy);
        builder.append(", defaultValue=");
        builder.append(defaultValue);
        builder.append(", modified=");
        builder.append(modified);
        builder.append(", created=");
        builder.append(created);
        builder.append(", encrypted=");
        builder.append(encrypted);
        builder.append(", valueType=");
        builder.append(valueType);
        builder.append(", optionName=");
        builder.append(optionName);
        builder.append(", applicationName=");
        builder.append(applicationName);
        builder.append(", description=");
        builder.append(description);
        builder.append(", getInitVector()=");
        builder.append(Arrays.toString(getInitVector()));
        builder.append(", getEncoded()=");
        builder.append(Arrays.toString(getEncoded()));
        builder.append(")");
        return builder.toString();
    }

}
