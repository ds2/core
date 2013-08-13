package ds2.oss.core.api.options;

import java.util.Date;
import java.util.List;

/**
 * A service to store and retrieve options and option values from various sources.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionStorageService {
    
    /**
     * Returns the option with the given identifier.
     * 
     * @param ident
     *            the identifier
     * @return the option, or null if not found
     */
    <V> Option getOptionByIdentifier(OptionIdentifier<V> ident);
    
    /**
     * Creates a new option value.
     * 
     * @param optionIdent
     *            the option identifier
     * @param ctx
     *            the option value context
     * @param scheduleDate
     *            the schedule date for this value
     * @param value
     *            the value of the option value
     * @return the created option value
     */
    <V> OptionValue createOptionValue(OptionIdentifier<V> optionIdent, OptionValueContext ctx, Date scheduleDate,
        String value);
    
    /**
     * Creates a new option.
     * 
     * @param ident
     *            the option identifier
     * @param val
     *            the value for the option
     * @return the created option
     */
    <V> Option createOption(OptionIdentifier<V> ident, V val);
    
    /**
     * Finds the best option value for the given context.
     * 
     * @param ident
     *            the option identifier
     * @param ctx
     *            the option value context
     * @return the option value to use. This MAY return a virtual option value with the option.
     */
    <V> OptionValue findBestOptionValueByContext(OptionIdentifier<V> ident, OptionValueContext ctx);
    
    /**
     * Returns all known options for the given application name.
     * 
     * @param appName
     *            the application name
     * @return A list of all known options.
     */
    List<Option> getAllOptions(String appName);
}
