package ds2.oss.core.dbtools.it;

import ds2.oss.core.api.EntryState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by dstrauss on 19.06.15.
 */
@Stateless
@TransactionAttribute
@TransactionManagement
public class MyEntityServiceBean implements MyEntityService {
    private static final Logger LOG= LoggerFactory.getLogger(MyEntityServiceBean.class);
    @PersistenceContext(unitName = "octest")
    private EntityManager em;

    public MyEntity create(String name, EntryState state){
        MyEntity m=new MyEntity();
        m.setDate(new Date());
        m.setName(name);
        m.setState(state);
        em.persist(m);
        return m;
    }

    public MyEntity getEntityById(long id){
        return em.find(MyEntity.class, id);
    }
}
