package ds2.oss.core.dbtools.it.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.dbtools.AbstractPersistenceSupportImpl;
import ds2.oss.core.dbtools.it.MyEntityService;
import ds2.oss.core.dbtools.it.entities.MyEntity;
import ds2.oss.core.dbtools.it.entities.StateEntity;

/**
 * Created by dstrauss on 19.06.15.
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class MyEntityServiceBean extends AbstractPersistenceSupportImpl<MyEntity, Long>implements MyEntityService {
    private static final Logger LOG = LoggerFactory.getLogger(MyEntityServiceBean.class);
    @PersistenceContext(unitName = "octest")
    private EntityManager em;
    @Inject
    private StateEntityOperations ses;
    
    @Override
    public MyEntity create(String name, EntryState state) {
        MyEntity m = new MyEntity();
        m.setDate(new Date());
        m.setName(name);
        StateEntity thisState = ses.getById(Long.valueOf(state.getNumericalValue()));
        m.setEntryState(thisState);
        em.persist(m);
        return m;
    }
    
    @Override
    public MyEntity getEntityById(long id) {
        return getSecureFindById(em, Long.valueOf(id));
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public MyEntity getById(Long e) {
        return getSecureFindById(em, e);
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(MyEntity t) {
        create(em, t);
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.dbtools.AbstractPersistenceSupportImpl#getEntityClass()
     */
    @Override
    protected Class<MyEntity> getEntityClass() {
        return MyEntity.class;
    }
}
