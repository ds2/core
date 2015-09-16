/**
 * 
 */
package ds2.oss.core.dbtools.modules;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ds2.oss.core.api.CreatedAware;

/**
 * @author dstrauss
 *         
 */
@Embeddable
public class CreatedAwareModule implements CreatedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -6249059771176243495L;
    /**
     * The creation date.
     */
    @Column(name = "created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();
    
    /**
     * Inits this object.
     */
    public CreatedAwareModule() {
        created = new Date();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedAware#getCreated()
     */
    @Override
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date d) {
        created = d;
    }
    
}
