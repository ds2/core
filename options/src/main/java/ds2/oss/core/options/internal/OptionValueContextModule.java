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
package ds2.oss.core.options.internal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeType;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.environment.ServerIdentifierDto;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.dbtools.converters.ClusterConverter;
import ds2.oss.core.dbtools.converters.RuntimeConfigurationConverter;

/**
 * A module to support the context of an option value.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Embeddable
public class OptionValueContextModule implements OptionValueContext {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -3742055339101918107L;
    /**
     * The requested domain.
     */
    @Column(name = "ctx_req_domain")
    private String requestedDomain;
    /**
     * The server identifier.
     */
    @Transient
    private ServerIdentifier server;
    /**
     * the server hostname.
     */
    @Column(name = "ctx_server_hostname")
    private String serverHostname;
    /**
     * The server domain.
     */
    @Column(name = "ctx_server_domain")
    private String serverDomain;
    /**
     * The server domain.
     */
    @Column(name = "ctx_server_address")
    private String serverIp;
    /**
     * The runtime config.
     */
    @Column(name = "ctx_runtime_config")
    @Convert(converter = RuntimeConfigurationConverter.class)
    private RuntimeType configuration;
    /**
     * The cluster.
     */
    @Column(name = "ctx_cluster")
    @Convert(converter = ClusterConverter.class)
    private Cluster cluster;
    
    @Override
    public Cluster getCluster() {
        return cluster;
    }
    
    @Override
    public RuntimeType getConfiguration() {
        return configuration;
    }
    
    @Override
    public String getRequestedDomain() {
        return requestedDomain;
    }
    
    @Override
    public ServerIdentifier getServer() {
        return toServerIdentifier();
    }
    
    /**
     * Sets the cluster.
     * 
     * @param cluster
     *            the cluster to set
     */
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    /**
     * Sets the runtime config.
     * 
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(RuntimeType configuration) {
        this.configuration = configuration;
    }
    
    /**
     * Sets the requested domain.
     * 
     * @param requestedDomain
     *            the requestedDomain to set
     */
    public void setRequestedDomain(String requestedDomain) {
        this.requestedDomain = requestedDomain;
    }
    
    /**
     * Sets the server identifier.
     * 
     * @param server
     *            the server to set
     */
    public void setServer(ServerIdentifier server) {
        this.server = server;
        if (server != null) {
            serverDomain = server.getDomain();
            serverHostname = server.getHostName();
            serverIp = server.getIpAddress();
        }
    }
    
    /**
     * Returns the server identifier based on this given context.
     * 
     * @return the server identifier
     */
    private ServerIdentifier toServerIdentifier() {
        ServerIdentifierDto rc = new ServerIdentifierDto();
        rc.setDomain(serverDomain);
        rc.setHostName(serverHostname);
        rc.setIpAddress(serverIp);
        if (rc.isEmpty()) {
            return null;
        }
        return null;
    }
    
}
