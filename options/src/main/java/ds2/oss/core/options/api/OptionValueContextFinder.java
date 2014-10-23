/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.options.OptionException;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.options.impl.entities.OptionValueContextEntity;

/**
 * A tool to work with sets of option value contexts.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionValueContextFinder {
    /**
     * Finds the context entity with the given context.
     * 
     * @param ctx
     *            the context
     * @return the context entity, or null if not found
     */
    OptionValueContextEntity findByContext(OptionValueContext ctx);
    
    /**
     * Same as {@link #findByContext(OptionValueContext)} but will create the entity if not found.
     * 
     * @param ctx
     *            the context
     * @return the context entity
     * @throws OptionException
     *             if an error occurred
     */
    OptionValueContextEntity findOrCreateContext(OptionValueContext ctx) throws OptionException;
}
