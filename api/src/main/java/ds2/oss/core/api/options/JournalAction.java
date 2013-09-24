/**
 * 
 */
package ds2.oss.core.api.options;

/**
 * The journal actions.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum JournalAction {
    /**
     * An option has been created.
     */
    CREATE_OPTION,
    /**
     * An option has been deleted.
     */
    DELETE_OPTION,
    /**
     * An option value has been created.
     */
    CREATE_OPTION_VALUE,
    /**
     * An option value has been authorized.
     */
    AUTHORIZE_OPTION_VALUE,
    /**
     * An option value has been updated.
     */
    UPDATE_OPTION_VALUE,
    /**
     * Deletes an option value.
     */
    DELETE_OPTION_VALUE;
}
