package ds2.oss.core.dbtools.it.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.Persistable;

/**
 * Created by dstrauss on 19.06.15.
 */
@Entity(name = "State")
@Table(name = "oc_state")
public class StateEntity implements EntryState, Persistable<Long> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5050515061070554228L;
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    
    public StateEntity() {
    }
    
    public StateEntity(String name) {
        this.name = name;
    }
    
    public StateEntity(int id, String name) {
        this(name);
        this.id = id;
    }
    
    @Override
    public String getEntryStateName() {
        return name;
    }
    
    @Override
    public int getNumericalValue() {
        return (int) id;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.Persistable#getId()
     */
    @Override
    public Long getId() {
        return Long.valueOf(id);
    }
}
