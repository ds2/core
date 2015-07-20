/**
 * 
 */
package ds2.oss.core.dbtools;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import ds2.oss.core.api.CreatedByModifiedByAware;

/**
 * @author dstrauss
 *         
 */
@MappedSuperclass
public class DefaultCreatedByModifiedByEntity extends AbstractCreatedModifiedEntity
    implements
    CreatedByModifiedByAware {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6836168305533415114L;
    @Column(name = "createdby", nullable = false)
    private String createdBy;
    @Column(name = "modifiedby")
    private String modifiedBy;
    
    /**
     * Sets the createdBy.
     * 
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * Sets the modifiedBy.
     * 
     * @param modifiedBy
     *            the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedByModifiedByAware#getCreatedBy()
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.CreatedByModifiedByAware#getModifiedBy()
     */
    @Override
    public String getModifiedBy() {
        return modifiedBy;
    }
    
}
