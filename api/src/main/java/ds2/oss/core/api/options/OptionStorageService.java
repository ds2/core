package ds2.oss.core.api.options;

import java.util.Date;
import java.util.List;

/**
 * A service to store and retrieve options and option values from various sources.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the type of the primary key
 */
public interface OptionStorageService<E> {
    
    /**
     * Returns the option with the given identifier.
     * 
     * @param ident
     *            the identifier
     * @param <V>
     *            the value type of the option
     * @return the option, or null if not found
     */
    <V> Option<E, V> getOptionByIdentifier(OptionIdentifier<V> ident);
    
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
     * @param <V>
     *            the value type
     * @return the created option value
     */
    <V> OptionValue<E, V> createOptionValue(OptionIdentifier<V> optionIdent, OptionValueContext ctx, Date scheduleDate,
        V value);
    
    /**
     * Creates a new option.
     * 
     * @param ident
     *            the option identifier
     * @param val
     *            the value for the option
     * @param <V>
     *            the value type
     * @return the created option
     */
    <V> Option<E, V> createOption(OptionIdentifier<V> ident, V val);
    
    /**
     * Finds the best option value for the given context.
     * 
     * @param ident
     *            the option identifier
     * @param ctx
     *            the option value context
     * @param <V>
     *            the value type
     * @return the option value to use. This MAY return a virtual option value with the option.
     */
    <V> OptionValue<E, V> findBestOptionValueByContext(OptionIdentifier<V> ident, OptionValueContext ctx);
    
    /**
     * Returns all known options for the given application name.
     * 
     * @param appName
     *            the application name
     * @return A list of all known options.
     */
    List<Option<E, ?>> getAllOptions(String appName);
}
