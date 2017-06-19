package ds2.oss.core.dbtools;

import ds2.oss.core.statics.Methods;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Set;

/**
 * Created by dstrauss on 16.06.17.
 */
public interface JpaHelper {
    static CriteriaQuery<Long> createCountQueryFromCriteria(CriteriaBuilder cb, CriteriaQuery<?> someQuery) {
        CriteriaQuery<Long> rc = cb.createQuery(Long.class);
        Set<Root<?>> roots = someQuery.getRoots();
        if (Methods.size(roots) == 1) {
            rc.select((cb.countDistinct(roots.iterator().next())));
        } else {
            //we have a problem :D
            //rc.select((cb.countDistinct()));
        }
        rc.where(someQuery.getRestriction());
        return rc;
    }
}
