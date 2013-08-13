package ds2.oss.core.api.options;

import ds2.oss.core.api.CreatedModifiedAware;
import ds2.oss.core.api.Persistable;

/**
 * The definition of a single option.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the persistable type
 * @param <V>
 *            the value type of the option
 */
public interface Option<E, V> extends Persistable<E>, OptionIdentifier<V>, CreatedModifiedAware {
    /**
     * Returns the default value of the option.
     * 
     * @return the default value
     */
    V getDefaultValue();
    
    /**
     * Returns the name or identifier of the option.
     * 
     * @return the identifier of the author/modifier
     */
    String getModifierName();
    
    /**
     * Returns the stage of the option.
     * 
     * @return the stage of the opion
     */
    OptionStage getStage();
}
