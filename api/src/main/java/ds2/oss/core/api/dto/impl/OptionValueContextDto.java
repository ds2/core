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

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeType;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.OptionValueContext;

/**
 * A simple option value context dto.
 *
 * @author dstrauss
 * @version 0.3
 */
@XmlType(name = "optionValueType")
@XmlAccessorType(XmlAccessType.FIELD)
public class OptionValueContextDto implements OptionValueContext {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = -805647699277313685L;
    /**
     * The cluster.
     */
    @XmlElement
    private Cluster cluster;
    /**
     * The runtime configuration.
     */
    @XmlElement
    private RuntimeType configuration;
    /**
     * The requested domain.
     */
    @XmlElement
    private String requestedDomain;
    /**
     * The server identifier.
     */
    @XmlElement
    private ServerIdentifier server;

    /**
     * Inits the dto with empty values.
     */
    public OptionValueContextDto() {
        // nothing to do
    }

    /**
     * Inits the dto with empty values.
     *
     * @param c
     *            the target cluster
     */
    public OptionValueContextDto(@NotNull final Cluster c) {
        this();
        cluster = c;
    }

    /**
     * Inits the dto.
     *
     * @param c
     *            the cluster
     * @param config
     *            the runtime configuration
     */
    public OptionValueContextDto(final Cluster c, final RuntimeType config) {
        this(c);
        configuration = config;
    }

    /**
     * Inits the dto.
     *
     * @param c
     *            the cluster
     * @param config
     *            the runtime configuration
     * @param reqDomain
     *            the requested domain
     */
    public OptionValueContextDto(final Cluster c, final RuntimeType config, final String reqDomain) {
        this(c, config);
        requestedDomain = reqDomain;
    }

    /**
     *
     * Inits the dto.
     *
     * @param c
     *            the cluster
     * @param config
     *            the runtime configuration
     * @param reqDomain
     *            the requested domain
     * @param serverIdentifier
     *            the server identifier
     */
    public OptionValueContextDto(final Cluster c, final RuntimeType config, final String reqDomain,
                                 final ServerIdentifier serverIdentifier) {
        this(c, config, reqDomain);
        server = serverIdentifier;
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OptionValueContextDto other = (OptionValueContextDto) obj;
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
        return true;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getCluster()
     */
    @Override
    public Cluster getCluster() {
        return cluster;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getConfiguration()
     */
    @Override
    public RuntimeType getConfiguration() {
        return configuration;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getRequestedDomain()
     */
    @Override
    public String getRequestedDomain() {
        return requestedDomain;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getServer()
     */
    @Override
    public ServerIdentifier getServer() {
        return server;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (cluster == null ? 0 : cluster.hashCode());
        result = prime * result + (configuration == null ? 0 : configuration.hashCode());
        result = prime * result + (requestedDomain == null ? 0 : requestedDomain.hashCode());
        result = prime * result + (server == null ? 0 : server.hashCode());
        return result;
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
     * The runtime config.
     *
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(final RuntimeType configuration) {
        this.configuration = configuration;
    }

    /**
     * Sets the requested domain.
     *
     * @param requestedDomain
     *            the requestedDomain to set
     */
    public void setRequestedDomain(final String requestedDomain) {
        this.requestedDomain = requestedDomain;
    }

    /**
     * Sets the server id.
     *
     * @param server
     *            the server to set
     */
    public void setServer(final ServerIdentifier server) {
        this.server = server;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("OptionValueContextDto(requestedDomain=");
        builder.append(requestedDomain);
        builder.append(", server=");
        builder.append(server);
        builder.append(", configuration=");
        builder.append(configuration);
        builder.append(", cluster=");
        builder.append(cluster);
        builder.append(")");
        return builder.toString();
    }

}
