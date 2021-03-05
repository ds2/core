/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.api.dto.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ds2.oss.core.api.EditableCreatedModifiedAware;
import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.ClusterDto;
import ds2.oss.core.api.environment.RuntimeType;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.api.options.ValueType;
import lombok.Getter;
import lombok.Setter;

/**
 * The option value dto.
 *
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the id type
 * @param <V>
 *            the value type
 */
@XmlType(name = "optionValueType")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class OptionValueDto<E, V> extends IvEncodedContentDto implements OptionValue<E, V>, EditableCreatedModifiedAware {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6404306264233903378L;
    /**
     * The approver name.
     */
    private String approverName;
    /**
     * The author name.
     */
    @NotNull
    private String authorName;
    /**
     * The cluster this value belongs to.
     */
    @XmlElement(type = ClusterDto.class)
    private Cluster cluster;
    /**
     * The server runtime configuration for this value.
     */
    private RuntimeType configuration;
    /**
     * The creation date.
     */
    private LocalDateTime created;
    @XmlAttribute
    private boolean encrypted;
    /**
     * The id of the value.
     */
    @XmlAttribute
    private E id;
    /**
     * The modification date.
     */
    private LocalDateTime modified;
    /**
     * The id of the referenced option.
     */
    @NotNull
    private E optionReference;
    /**
     * The requested domain.
     */
    private String requestedDomain;
    /**
     * The server identifier this value belongs to.
     */
    private ServerIdentifier server;
    /**
     * The stage for this value.
     */
    @NotNull
    private OptionValueStage stage;
    /**
     * The unencrypted value.
     */
    @XmlTransient
    private transient V unencryptedValue;
    /**
     * The start date for this value.
     */
    @NotNull
    private LocalDateTime validFrom;
    /**
     * The stop date for this value.
     */
    private LocalDateTime validTo;
    /**
     * The value.
     */
    private V value;
    /**
     * the value type.
     */
    @XmlElement
    @NotNull
    private ValueType valueType;
    private String createdBy;
    private String modifiedBy;

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
        OptionValueDto other = (OptionValueDto) obj;
        if (approverName == null) {
            if (other.approverName != null) {
                return false;
            }
        } else if (!approverName.equals(other.approverName)) {
            return false;
        }
        if (authorName == null) {
            if (other.authorName != null) {
                return false;
            }
        } else if (!authorName.equals(other.authorName)) {
            return false;
        }
        if (cluster == null) {
            if (other.cluster != null) {
                return false;
            }
        } else if (!cluster.equals(other.cluster)) {
            return false;
        }
        if (configuration != other.configuration) {
            return false;
        }
        if (created == null) {
            if (other.created != null) {
                return false;
            }
        } else if (!created.equals(other.created)) {
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
        if (optionReference == null) {
            if (other.optionReference != null) {
                return false;
            }
        } else if (!optionReference.equals(other.optionReference)) {
            return false;
        }
        if (requestedDomain == null) {
            if (other.requestedDomain != null) {
                return false;
            }
        } else if (!requestedDomain.equals(other.requestedDomain)) {
            return false;
        }
        if (server == null) {
            if (other.server != null) {
                return false;
            }
        } else if (!server.equals(other.server)) {
            return false;
        }
        if (stage != other.stage) {
            return false;
        }
        if (validFrom == null) {
            if (other.validFrom != null) {
                return false;
            }
        } else if (!validFrom.equals(other.validFrom)) {
            return false;
        }
        if (validTo == null) {
            if (other.validTo != null) {
                return false;
            }
        } else if (!validTo.equals(other.validTo)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
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
        result = prime * result + (approverName == null ? 0 : approverName.hashCode());
        result = prime * result + (authorName == null ? 0 : authorName.hashCode());
        result = prime * result + (cluster == null ? 0 : cluster.hashCode());
        result = prime * result + (configuration == null ? 0 : configuration.hashCode());
        result = prime * result + (created == null ? 0 : created.hashCode());
        result = prime * result + (encrypted ? 1231 : 1237);
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (modified == null ? 0 : modified.hashCode());
        result = prime * result + (optionReference == null ? 0 : optionReference.hashCode());
        result = prime * result + (requestedDomain == null ? 0 : requestedDomain.hashCode());
        result = prime * result + (server == null ? 0 : server.hashCode());
        result = prime * result + (stage == null ? 0 : stage.hashCode());
        result = prime * result + (validFrom == null ? 0 : validFrom.hashCode());
        result = prime * result + (validTo == null ? 0 : validTo.hashCode());
        result = prime * result + (value == null ? 0 : value.hashCode());
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
        builder.append("OptionValueDto(");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        if (created != null) {
            builder.append("created=");
            builder.append(created);
            builder.append(", ");
        }
        if (modified != null) {
            builder.append("modified=");
            builder.append(modified);
            builder.append(", ");
        }
        if (validFrom != null) {
            builder.append("validFrom=");
            builder.append(validFrom);
            builder.append(", ");
        }
        if (validTo != null) {
            builder.append("validTo=");
            builder.append(validTo);
            builder.append(", ");
        }
        if (cluster != null) {
            builder.append("cluster=");
            builder.append(cluster);
            builder.append(", ");
        }
        if (configuration != null) {
            builder.append("configuration=");
            builder.append(configuration);
            builder.append(", ");
        }
        if (server != null) {
            builder.append("server=");
            builder.append(server);
            builder.append(", ");
        }
        if (approverName != null) {
            builder.append("approverName=");
            builder.append(approverName);
            builder.append(", ");
        }
        if (authorName != null) {
            builder.append("authorName=");
            builder.append(authorName);
            builder.append(", ");
        }
        if (optionReference != null) {
            builder.append("optionReference=");
            builder.append(optionReference);
            builder.append(", ");
        }
        if (stage != null) {
            builder.append("stage=");
            builder.append(stage);
            builder.append(", ");
        }
        if (value != null) {
            builder.append("value=");
            builder.append(value);
            builder.append(", ");
        }
        if (requestedDomain != null) {
            builder.append("requestedDomain=");
            builder.append(requestedDomain);
            builder.append(", ");
        }
        if (valueType != null) {
            builder.append("valueType=");
            builder.append(valueType);
            builder.append(", ");
        }
        builder.append("encrypted=");
        builder.append(encrypted);
        builder.append(", ");
        if (getInitVector() != null) {
            builder.append("getInitVector()=");
            builder.append(Arrays.toString(getInitVector()));
            builder.append(", ");
        }
        if (getEncoded() != null) {
            builder.append("getEncoded()=");
            builder.append(Arrays.toString(getEncoded()));
        }
        builder.append(")");
        return builder.toString();
    }

}
