package ds2.oss.core.dbtools.it.entities;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ds2.oss.core.api.Persistable;
import ds2.oss.core.dbtools.AbstractStateAwareBase;

/**
 * Created by dstrauss on 19.06.15.
 */
@Entity(name = "MyE")
@Table(name = "oc_my")
@AssociationOverrides({
    @AssociationOverride(name = "entryState", joinColumns = @JoinColumn(name = "state_id", nullable = false) ) })
public class MyEntity extends AbstractStateAwareBase<StateEntity>implements Persistable<Long> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -3112335945264097331L;
    @Id
    @Column
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    
    @Override
    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
}
