/**
 * 
 */
package ds2.oss.core.options.impl.dto;

import javax.persistence.Embeddable;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.EnumModule;

/**
 * The value type module.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Embeddable
public class ValueTypeModule extends EnumModule<ValueType> {
    private int value;
    
    public ValueTypeModule() {
        super(ValueType.class);
    }
    
    @Override
    public void setValue(final ValueType e) {
        super.setValue(e);
    }
    
    @Override
    public ValueType getValue() {
        return super.getValue();
    }
    
}
