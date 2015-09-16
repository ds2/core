/**
 * 
 */
package ds2.oss.core.dbtools.modules;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ds2.oss.core.api.ModifiedAware;

/**
 * @author dstrauss
 *         
 */
@Embeddable
public class ModifiedAwareModule implements ModifiedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 4346982418358790652L;
    /**
     * The modification date.
     */
    @Column(name = "modified", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    
    /**
     * Inits this object.
     */
    public ModifiedAwareModule() {
        modified = new Date();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.ModifiedAware#getModified()
     */
    @Override
    public Date getModified() {
        return modified;
    }
    
    /**
     * Updates the modified date.
     */
    public final void touchModified() {
        modified = new Date();
    }
    
    /**
     * @param d
     */
    public void setModified(Date d) {
        if (d != null) {
            modified = d;
        }
    }
}
