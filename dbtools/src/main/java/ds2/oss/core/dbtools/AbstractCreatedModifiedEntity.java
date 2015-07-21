package ds2.oss.core.dbtools;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import ds2.oss.core.api.CreatedModifiedAware;
import ds2.oss.core.dbtools.modules.CreatedModifiedAwareModule;

/**
 * Created by dstrauss on 18.06.15.
 */

@MappedSuperclass
public abstract class AbstractCreatedModifiedEntity implements CreatedModifiedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8846962750701956032L;
    /**
     * The embeddable to handle the columns.
     */
    @Embedded
    private CreatedModifiedAwareModule cma = new CreatedModifiedAwareModule();
    
    @Override
    public Date getCreated() {
        if (cma == null) {
            cma = new CreatedModifiedAwareModule();
        }
        return cma.getCreated();
    }
    
    /**
     * Sets the creation date.
     * 
     * @param date
     *            the creation date.
     */
    public void setCreated(Date date) {
        if (cma == null) {
            cma = new CreatedModifiedAwareModule();
        }
        cma.setCreated(date);
    }
    
    @Override
    public Date getModified() {
        if (cma == null) {
            cma = new CreatedModifiedAwareModule();
        }
        return cma.getModified();
    }
    
    /**
     * Sets the modified date. Usually you want to use {@link #touchModified()} instead.
     * 
     * @param d
     *            the modification date
     */
    public void setModified(Date d) {
        if (cma == null) {
            cma = new CreatedModifiedAwareModule();
        }
        cma.setModified(d);
    }
    
    public void touchModified() {
        if (cma == null) {
            cma = new CreatedModifiedAwareModule();
        }
        cma.touchModified();
    }
}
