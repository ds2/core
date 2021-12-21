/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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

import ds2.oss.core.api.Persistable;
import ds2.oss.core.dbtools.AbstractStateAwareBase;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Created by dstrauss on 19.06.15.
 */
@Entity(name = "MyE")
@Table(name = "oc_my")
@AssociationOverrides({
        @AssociationOverride(name = "entryState", joinColumns = @JoinColumn(name = "state_id", nullable = false))})
public class MyEntity extends AbstractStateAwareBase<StateEntity> implements Persistable<Long> {
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
