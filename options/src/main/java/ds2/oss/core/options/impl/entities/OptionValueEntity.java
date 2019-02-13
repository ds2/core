/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.options.impl.entities;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeType;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.dbtools.AbstractCreatedByModifiedByEntity;
import ds2.oss.core.dbtools.modules.IvEncodedContentModule;
import ds2.oss.core.dbtools.modules.LifeCycleAwareModule;
import ds2.oss.core.options.internal.OptionValueContextModule;
import ds2.oss.core.options.internal.OptionValueStageConverter;
import ds2.oss.core.options.internal.ValueTypeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The option values.
 *
 * @author dstrauss
 * @version 0.3
 */
@Entity(name = "coreOptionValue")
@Table(name = "core_optionvalues", uniqueConstraints = {@UniqueConstraint(columnNames = {"ref_option", "ctx_cluster",
        "ctx_runtime_config", "ctx_req_domain", "ctx_server_hostname", "ctx_server_domain", "ctx_server_address"})})
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
public class OptionValueEntity extends AbstractCreatedByModifiedByEntity implements OptionValue<Long, Object> {

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
        super();
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
    public RuntimeType getConfiguration() {
        return ctx.getConfiguration();
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
    public Object getValue() {
        return value;
    }

    @Override
    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public boolean isEncrypted() {
        return ecm.getEncoded() != null;
    }

    /**
     * Sets the approver name.
     *
     * @param approverName the approverName to set
     */
    public void setApproverName(final String approverName) {
        this.approverName = approverName;
    }

    /**
     * Sets the author name.
     *
     * @param authorName the authorName to set
     */
    public void setAuthorName(final String authorName) {
        this.authorName = authorName;
    }

    /**
     * Sets the cluster.
     *
     * @param cluster the cluster
     */
    public void setCluster(Cluster cluster) {
        ctx.setCluster(cluster);
    }

    /**
     * Sets the configuration.
     *
     * @param configuration the runtime configuration
     */
    public void setConfiguration(RuntimeType configuration) {
        ctx.setConfiguration(configuration);
    }

    /**
     * Sets the encoded content.
     *
     * @param encoded the encoded content
     */
    public void setEncoded(byte[] encoded) {
        ecm.setEncoded(encoded);
    }

    /**
     * Sets a new id.
     *
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Sets the init vector.
     *
     * @param initVector the init vector
     */
    public void setInitVector(byte[] initVector) {
        ecm.setInitVector(initVector);
    }

    /**
     * Sets a new referenced option.
     *
     * @param refOption the refOption to set
     */
    public void setRefOption(final Option<Long, ?> refOption) {
        this.refOption = refOption;
    }

    /**
     * Sets the requested domain.
     *
     * @param requestedDomain the requested domain
     */
    public void setRequestedDomain(String requestedDomain) {
        ctx.setRequestedDomain(requestedDomain);
    }

    /**
     * Sets the server identifier.
     *
     * @param server the server identifier
     */
    public void setServer(ServerIdentifier server) {
        ctx.setServer(server);
    }

    /**
     * Sets a new stage value.
     *
     * @param stage the stage to set
     */
    public void setStage(final OptionValueStage stage) {
        this.stage = stage;
    }

    /**
     * Sets the start date.
     *
     * @param validFrom the start date
     */
    public void setValidFrom(LocalDateTime validFrom) {
        lca.setValidFrom(validFrom);
    }

    /**
     * Sets the end date.
     *
     * @param validTo the end date
     */
    public void setValidTo(LocalDateTime validTo) {
        lca.setValidTo(validTo);
    }

    /**
     * Sets a new value.
     *
     * @param value the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Sets the value type.
     *
     * @param t the value type
     */
    public void setValueType(ValueType t) {
        valueType = t;
    }

    @Override
    public LocalDateTime getValidFrom() {
        return lca.getValidFrom();
    }

    @Override
    public LocalDateTime getValidTo() {
        return lca.getValidTo();
    }
}
