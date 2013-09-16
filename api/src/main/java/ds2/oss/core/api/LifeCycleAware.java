package ds2.oss.core.api;

import java.io.Serializable;
import java.util.Date;

/**
 * A life cycle aware component contract.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface LifeCycleAware extends Serializable {
    Date getValidFrom();
    
    Date getValidTo();
}
