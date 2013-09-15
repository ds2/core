/**
 * 
 */
package ds2.oss.core.options.impl;

import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.base.impl.AbstractEnumPersistenceSupport;

/**
 * The option stage persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class OptionStagePersistenceSupport extends AbstractEnumPersistenceSupport<OptionStage> {
    
    /**
     * Inits the support.
     */
    public OptionStagePersistenceSupport() {
        super();
        fillLookup(OptionStage.values());
    }
    
}
