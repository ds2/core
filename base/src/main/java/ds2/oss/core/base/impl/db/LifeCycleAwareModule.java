/**
 * 
 */
package ds2.oss.core.base.impl.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ds2.oss.core.api.LifeCycleAware;

/**
 * A life cycle aware db module.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@Embeddable
public class LifeCycleAwareModule implements LifeCycleAware {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6897754896689099253L;
    /**
     * Valid from.
     */
    @Column(name = "valid_from", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    /**
     * Valid to.
     */
    @Column(name = "valid_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    
    /**
     * Inits the module.
     */
    public LifeCycleAwareModule() {
        // TODO Auto-generated constructor stub
    }
    
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }
    
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.LifeCycleAware#getValidFrom()
     */
    @Override
    public Date getValidFrom() {
        return validFrom;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.LifeCycleAware#getValidTo()
     */
    @Override
    public Date getValidTo() {
        return validTo;
    }
    
}
