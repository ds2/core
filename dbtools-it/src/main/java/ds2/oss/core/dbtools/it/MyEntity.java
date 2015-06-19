package ds2.oss.core.dbtools.it;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.StateAware;

/**
 * Created by dstrauss on 19.06.15.
 */
@Entity
@Table(name = "oc_my")
public class MyEntity implements StateAware {
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
    @JoinColumn(name = "state", nullable = false)
    @ManyToOne(targetEntity = StateEntity.class)
    private EntryState state;
    
    @Override
    public EntryState getEntryState() {
        return state;
    }
    
    public long getId() {
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
    
    public void setState(EntryState state) {
        this.state = state;
    }
}
