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
import ds2.oss.core.dbtools.it.MyEntityService;
import ds2.oss.core.dbtools.it.entities.MyEntity;

/**
 * Created by dstrauss on 19.06.15.
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class MyEntityServiceBean implements MyEntityService {
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
        m.setEntryState(ses.getById(em, state.getNumericalValue()));
        em.persist(m);
        return m;
    }
    
    @Override
    public MyEntity getEntityById(long id) {
        return em.find(MyEntity.class, id);
    }
}
