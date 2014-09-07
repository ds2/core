package ds2.oss.core.api.dto.impl;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
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
     * The runtime configuration.
     */
    @XmlElement
    private RuntimeConfiguration configuration;
    /**
     * The cluster.
     */
    @XmlElement
    private Cluster cluster;
    
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
     * Sets the requested domain.
     * 
     * @param requestedDomain
     *            the requestedDomain to set
     */
    public void setRequestedDomain(String requestedDomain) {
        this.requestedDomain = requestedDomain;
    }
    
    /**
     * Sets the server id.
     * 
     * @param server
     *            the server to set
     */
    public void setServer(ServerIdentifier server) {
        this.server = server;
    }
    
    /**
     * The runtime config.
     * 
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(RuntimeConfiguration configuration) {
        this.configuration = configuration;
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
    public RuntimeConfiguration getConfiguration() {
        return configuration;
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
     * @see ds2.oss.core.api.options.OptionValueContext#getRequestedDomain()
     */
    @Override
    public String getRequestedDomain() {
        return requestedDomain;
    }
    
}
