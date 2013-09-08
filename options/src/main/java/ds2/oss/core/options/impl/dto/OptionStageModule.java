/**
 * 
 */
package ds2.oss.core.options.impl.dto;

import javax.persistence.Embeddable;

import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.base.impl.EnumModule;

/**
 * The option stage module.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Embeddable
public class OptionStageModule extends EnumModule<OptionStage> {
    private int value;
    
    /**
     * Inits the module.
     */
    public OptionStageModule() {
        super(OptionStage.class);
    }
    
    @Override
    public OptionStage getValue() {
        return super.getValue();
    }
    
    @Override
    public void setValue(final OptionStage e) {
        super.setValue(e);
    }
}
