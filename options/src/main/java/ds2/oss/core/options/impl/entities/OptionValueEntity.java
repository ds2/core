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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.base.impl.db.CreatedModifiedAwareModule;
import ds2.oss.core.base.impl.db.IvEncodedContentModule;
import ds2.oss.core.base.impl.db.LifeCycleAwareModule;
import ds2.oss.core.options.internal.OptionValueContextModule;
import ds2.oss.core.options.internal.OptionValueStageConverter;

/**
 * The option values.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Entity(name = "coreOptionValue")
@Table(name = "core_optionvalues", uniqueConstraints = { @UniqueConstraint(
    columnNames = { "ref_option", "ctx_cluster", "ctx_runtime_config", "ctx_req_domain" }) })
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
     * The unencrypted value.
     */
    @Transient
    private Object unencryptedValue;
    /**
     * The CMA contract.
     */
    @Embedded
    private CreatedModifiedAwareModule cma;
    /**
     * The lifecycle module.
     */
    @Embedded
    private LifeCycleAwareModule lca;
    /**
     * The id of the entry.
     */
    @Id
    @GeneratedValue(generator = "tableGen2", strategy = GenerationType.TABLE)
    private Long id;
    /**
     * The option value ctx.
     */
    @Embedded
    private OptionValueContextModule ctx;
    /**
     * The approver.
     */
    @Column(name = "approver", nullable = false)
    private String approverName;
    /**
     * The author.
     */
    @Column(name = "author", nullable = false, updatable = false)
    private String authorName;
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
    @Column(name = "value", nullable = false)
    private String value;
    /**
     * The EC module.
     */
    @Embedded
    private IvEncodedContentModule ecm;
    
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
    public Long getId() {
        return id;
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
    public Date getValidFrom() {
        return lca.getValidFrom();
    }
    
    @Override
    public Date getValidTo() {
        return lca.getValidTo();
    }
    
    @Override
    public Cluster getCluster() {
        return ctx.getCluster();
    }
    
    @Override
    public RuntimeConfiguration getConfiguration() {
        return ctx.getConfiguration();
    }
    
    @Override
    public ServerIdentifier getServer() {
        return ctx.getServer();
    }
    
    @Override
    public String getRequestedDomain() {
        return ctx.getRequestedDomain();
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
    public Long getOptionReference() {
        return refOption.getId();
    }
    
    @Override
    public OptionValueStage getStage() {
        return stage;
    }
    
    @Override
    public Object getValue() {
        return value;
    }
    
    @Override
    public Object getUnencryptedValue() {
        return unencryptedValue;
    }
    
    @Override
    public byte[] getInitVector() {
        return ecm.getInitVector();
    }
    
    @Override
    public byte[] getEncoded() {
        return ecm.getEncoded();
    }
    
}
