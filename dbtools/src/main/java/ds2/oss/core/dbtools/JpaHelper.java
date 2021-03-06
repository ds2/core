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
