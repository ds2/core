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
package ds2.oss.core.options.impl.entities;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.db.CreatedModifiedAwareModule;
import ds2.oss.core.base.impl.db.IvEncodedContentModule;
import ds2.oss.core.base.impl.db.LifeCycleAwareModule;
import ds2.oss.core.options.internal.OptionValueContextModule;
import ds2.oss.core.options.internal.OptionValueStageConverter;
import ds2.oss.core.options.internal.ValueTypeConverter;

/**
 * The option values.
 *
 * @author dstrauss
 * @version 0.3
 *
 */
@Entity(name = "coreOptionValue")
@Table(name = "core_optionvalues", uniqueConstraints = { @UniqueConstraint(columnNames = { "ref_option", "ctx_cluster",
    "ctx_runtime_config", "ctx_req_domain" }) })
@TableGenerator(
    name = "tableGen2",
    initialValue = 1,
    pkColumnName = "table_name",
    pkColumnValue = "core_optionvalues",
    table = "core_id",
    valueColumnName = "next_id",
    allocationSize = 1)
@SequenceGenerator(initialValue = 1, name = "seqGen2", sequenceName = "SEQ_CORE_OPTIONVALUES", allocationSize = 1)
@Access(AccessType.FIELD)
public class OptionValueEntity implements OptionValue<Long, Object> {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8443176889297017343L;
    
    /**
     * The approver.
     */
    @Column(name = "approver")
    private String approverName;
    
    /**
     * The author.
     */
    @Column(name = "author", nullable = false, updatable = false)
    private String authorName;
    
    /**
     * The CMA contract.
     */
    @Embedded
    private CreatedModifiedAwareModule cma;
    
    /**
     * The option value ctx.
     */
    @Embedded
    private OptionValueContextModule ctx = new OptionValueContextModule();
    
    /**
     * The EC module.
     */
    @Embedded
    private IvEncodedContentModule ecm = new IvEncodedContentModule();
    
    /**
     * The id of the entry.
     */
    @Id
    @GeneratedValue(generator = "tableGen2", strategy = GenerationType.TABLE)
    private Long id;
    
    /**
     * The lifecycle module.
     */
    @Embedded
    private LifeCycleAwareModule lca;
    
    /**
     * The ref option.
     */
    @JoinColumn(name = "ref_option", nullable = false)
    @ManyToOne(targetEntity = OptionEntity.class)
    private Option<Long, ?> refOption;
    /**
     * The option value stage.
     */
    @Column(name = "stage", nullable = false)
    @Convert(converter = OptionValueStageConverter.class)
    private OptionValueStage stage;
    /**
     * The value.
     */
    @Column(name = "value")
    private String value;
    /**
     * The value type.
     */
    @Column(name = "value_type", nullable = false)
    @Convert(converter = ValueTypeConverter.class)
    private ValueType valueType;
    
    /**
     * Inits the entity.
     */
    public OptionValueEntity() {
        cma = new CreatedModifiedAwareModule();
        ctx = new OptionValueContextModule();
        lca = new LifeCycleAwareModule();
        ecm = new IvEncodedContentModule();
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
    public Cluster getCluster() {
        if (ctx == null) {
            ctx = new OptionValueContextModule();
        }
        return ctx.getCluster();
    }
    
    @Override
    public RuntimeConfiguration getConfiguration() {
        return ctx.getConfiguration();
    }
    
    @Override
    public Date getCreated() {
        return cma.getCreated();
    }
    
    @Override
    public byte[] getEncoded() {
        if (ecm == null) {
            ecm = new IvEncodedContentModule();
        }
        return ecm.getEncoded();
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public byte[] getInitVector() {
        return ecm.getInitVector();
    }
    
    @Override
    public Date getModified() {
        return cma.getModified();
    }
    
    @Override
    public Long getOptionReference() {
        return refOption.getId();
    }
    
    /**
     * Returns the referenced option.
     *
     * @return the refOption
     */
    public Option<Long, ?> getRefOption() {
        return refOption;
    }
    
    @Override
    public String getRequestedDomain() {
        return ctx.getRequestedDomain();
    }
    
    @Override
    public ServerIdentifier getServer() {
        return ctx.getServer();
    }
    
    @Override
    public OptionValueStage getStage() {
        return stage;
    }
    
    @Override
    public Object getUnencryptedValue() {
        return null;
    }
    
    @Override
    public Date getValidFrom() {
        return lca.getValidFrom();
    }
    
    @Override
    public Date getValidTo() {
        return lca.getValidTo();
    }
    
    @Override
    public Object getValue() {
        return value;
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
     * Sets a new id.
     *
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }
    
    /**
     * Sets a new referenced option.
     *
     * @param refOption
     *            the refOption to set
     */
    public void setRefOption(final Option<Long, ?> refOption) {
        this.refOption = refOption;
    }
    
    /**
     * Sets a new stage value.
     *
     * @param stage
     *            the stage to set
     */
    public void setStage(final OptionValueStage stage) {
        this.stage = stage;
    }
    
    /**
     * Sets a new value.
     *
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }
    
    public void setCreated(Date date) {
        cma.setCreated(date);
    }
    
    public void setModified(Date date) {
        cma.setModified(date);
    }
    
    public void setCluster(Cluster cluster) {
        ctx.setCluster(cluster);
    }
    
    public void setConfiguration(RuntimeConfiguration configuration) {
        ctx.setConfiguration(configuration);
    }
    
    public void setEncoded(byte[] encoded) {
        ecm.setEncoded(encoded);
    }
    
    public void setInitVector(byte[] initVector) {
        ecm.setInitVector(initVector);
    }
    
    public void setRequestedDomain(String requestedDomain) {
        ctx.setRequestedDomain(requestedDomain);
    }
    
    public void setServer(ServerIdentifier server) {
        ctx.setServer(server);
    }
    
    public void setValidFrom(Date validFrom) {
        lca.setValidFrom(validFrom);
    }
    
    public void setValidTo(Date validTo) {
        lca.setValidTo(validTo);
    }
    
    @Override
    public ValueType getValueType() {
        return valueType;
    }
    
    public void setValueType(ValueType t) {
        valueType = t;
    }
    
    @Override
    public boolean isEncrypted() {
        return ecm.getEncoded() != null;
    }
    
}
