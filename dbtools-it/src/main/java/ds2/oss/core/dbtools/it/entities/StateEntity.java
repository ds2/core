/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
