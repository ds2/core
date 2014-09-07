package ds2.oss.core.options.impl.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import ds2.oss.core.api.environment.ServerIdentifier;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.options.internal.OptionValueContextModule;

/**
 * A table with all known option value contexts.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Table(name = "core_ctx")
@Entity
public class OptionValueContextEntity implements OptionValueContext {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 2616615585525973642L;
    /**
     * The id of an entry.
     */
    @Id
    private Long id;
    /**
     * The module with all known values.
     */
    @Embedded
    private OptionValueContextModule mod = new OptionValueContextModule();
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getCluster()
     */
    @Override
    public Cluster getCluster() {
        return mod.getCluster();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getConfiguration()
     */
    @Override
    public RuntimeConfiguration getConfiguration() {
        return mod.getConfiguration();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getServer()
     */
    @Override
    public ServerIdentifier getServer() {
        return mod.getServer();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionValueContext#getRequestedDomain()
     */
    @Override
    public String getRequestedDomain() {
        return mod.getRequestedDomain();
    }
    
}
