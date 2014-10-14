/**
 * License data comes here.
 */
package ds2.oss.core.base.impl.db;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Some class.
 *
 * @author dstrauss
 * @version 0.1
 *
 */
@StaticMetamodel(LifeCycleAwareModule.class)
public class LifeCycleAwareModule_ {
    
    public static volatile SingularAttribute<LifeCycleAwareModule, Date> validFrom;
    public static volatile SingularAttribute<LifeCycleAwareModule, Date> validTo;
    
}
