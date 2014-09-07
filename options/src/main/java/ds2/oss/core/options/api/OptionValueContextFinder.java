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
    OptionValueContextEntity findByContext(OptionValueContext ctx);
    
    OptionValueContextEntity findOrCreateContext(OptionValueContext ctx) throws OptionException;
}
