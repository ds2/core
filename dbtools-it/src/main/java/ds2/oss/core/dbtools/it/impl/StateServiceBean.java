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
package ds2.oss.core.dbtools.it.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.dbtools.it.StateService;
import ds2.oss.core.dbtools.it.entities.StateEntity;

/**
 * @author dstrauss
 *
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class StateServiceBean implements StateService {
    @PersistenceContext(unitName = "octest")
    private EntityManager em;
    
    @Override
    public EntryState createStateByName(String s) {
        StateEntity se = new StateEntity(s);
        em.persist(se);
        return se;
    }
    
}
