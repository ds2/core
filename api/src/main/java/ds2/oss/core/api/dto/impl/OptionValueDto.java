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
/**
 * 
 */
package ds2.oss.core.api.dto.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.ClusterDto;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueStage;

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
public class OptionValueDto<E, V> implements OptionValue<E, V> {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6404306264233903378L;
    /**
     * The id of the value.
     */
    @XmlAttribute
    private E id;
    /**
     * The creation date.
     */
    private Date created;
    /**
     * The modification date.
     */
    private Date modified;
    /**
     * The start date for this value.
     */
    private Date validFrom;
    /**
     * The stop date for this value.
     */
    private Date validTo;
    /**
     * The cluster this value belongs to.
     */
    @XmlElement(type = ClusterDto.class)
    private Cluster cluster;
    /**
     * The server runtime configuration for this value.
     */
    private RuntimeConfiguration configuration;
    /**
     * The server identifier this value belongs to.
     */
    private ServerIdentifier server;
    /**
     * The approver name.
     */
    private String approverName;
    /**
     * The author name.
     */
    private String authorName;
    /**
     * The id of the referenced option.
     */
    private E optionReference;
    /**
     * The stage for this value.
     */
    private OptionValueStage stage;
    /**
     * The value.
     */
    private V value;
    /**
     * The requested domain.
     */
    private String requestedDomain;
    /**
     * The unencrypted value.
     */
    @XmlTransient
    private transient V unencryptedValue;
    
    /**
     * Sets the unencrypted value.
     * 
     * @param unencryptedValue
     *            the unencrypted value
     */
    public void setUnencryptedValue(V unencryptedValue) {
        this.unencryptedValue = unencryptedValue;
    }
    
    @Override
    public E getId() {
        return id;
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
    public Date getValidFrom() {
        return validFrom;
    }
    
    @Override
    public Date getValidTo() {
        return validTo;
    }
    
    @Override
    public Cluster getCluster() {
        return cluster;
    }
    
    @Override
    public RuntimeConfiguration getConfiguration() {
        return configuration;
    }
    
    @Override
    public ServerIdentifier getServer() {
        return server;
    }
    
    @Override
    public String getApproverName() {
        return approverName;
    }
    
    @Override
    public String getAuthorName() {
        return authorName;
    }
    
    @Override
    public E getOptionReference() {
        return optionReference;
    }
    
    @Override
    public OptionValueStage getStage() {
        return stage;
    }
    
    @Override
    public V getValue() {
        return value;
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
     * Sets the creation date.
     * 
     * @param created
     *            the created to set
     */
    public void setCreated(final Date created) {
        this.created = created;
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
     * Sets the valid from date.
     * 
     * @param validFrom
     *            the validFrom to set
     */
    public void setValidFrom(final Date validFrom) {
        this.validFrom = validFrom;
    }
    
    /**
     * Sets the validTo date.
     * 
     * @param validTo
     *            the validTo to set
     */
    public void setValidTo(final Date validTo) {
        this.validTo = validTo;
    }
    
    /**
     * Sets the cluster.
     * 
     * @param cluster
     *            the cluster to set
     */
    public void setCluster(final Cluster cluster) {
        this.cluster = cluster;
    }
    
    /**
     * Sets the runtime config.
     * 
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(final RuntimeConfiguration configuration) {
        this.configuration = configuration;
    }
    
    /**
     * Sets the server identifier.
     * 
     * @param server
     *            the server to set
     */
    public void setServer(final ServerIdentifier server) {
        this.server = server;
    }
    
    /**
     * Sets the approver name.
     * 
     * @param approverName
     *            the approverName to set
     */
    public void setApproverName(final String approverName) {
        this.approverName = approverName;
    }
    
    /**
     * Sets the author name.
     * 
     * @param authorName
     *            the authorName to set
     */
    public void setAuthorName(final String authorName) {
        this.authorName = authorName;
    }
    
    /**
     * Sets the option reference id.
     * 
     * @param optionReference
     *            the optionReference to set
     */
    public void setOptionReference(final E optionReference) {
        this.optionReference = optionReference;
    }
    
    /**
     * Sets the value stage.
     * 
     * @param stage
     *            the stage to set
     */
    public void setStage(final OptionValueStage stage) {
        this.stage = stage;
    }
    
    /**
     * Sets the value.
     * 
     * @param value
     *            the value to set
     */
    public void setValue(final V value) {
        this.value = value;
    }
    
    @Override
    public String getRequestedDomain() {
        return requestedDomain;
    }
    
    public void setRequestedDomain(String requestedDomain) {
        this.requestedDomain = requestedDomain;
    }
    
    @Override
    public V getUnencryptedValue() {
        return unencryptedValue;
    }
    
}
