/**
 * 
 */
package ds2.oss.core.dbtools;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import ds2.oss.core.api.CreatedAware;
import ds2.oss.core.dbtools.modules.CreatedAwareModule;

/**
 * @author dstrauss
 *         
 */
@MappedSuperclass
public abstract class AbstractCreatedEntity implements CreatedAware {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8489399779935854162L;
    /**
     * The ca module.
     */
    @Embedded
    private CreatedAwareModule cam = new CreatedAwareModule();
    
    /**
     * Inits this object.
     */
    public AbstractCreatedEntity() {
        if (cam == null) {
            cam = new CreatedAwareModule();
        }
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedAware#getCreated()
     */
    @Override
    public Date getCreated() {
        if (cam == null) {
            cam = new CreatedAwareModule();
        }
        return cam.getCreated();
    }
    
    /**
     * Sets the creation date.
     * 
     * @param date
     *            the creation date.
     */
    public void setCreated(Date date) {
        if (cam == null) {
            cam = new CreatedAwareModule();
        }
        cam.setCreated(date);
    }
    
}
