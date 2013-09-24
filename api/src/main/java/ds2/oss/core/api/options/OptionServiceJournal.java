/**
 * 
 */
package ds2.oss.core.api.options;

/**
 * A journal service to tell when something has changed.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionServiceJournal {
    /**
     * Adds an entry.
     * 
     * @param invoker
     *            the invoker username
     * @param action
     *            the action
     * @param affectedId
     *            the affected id
     * @param oldVal
     *            the old value
     * @param newVal
     *            the new value
     */
    void addEntry(String invoker, JournalAction action, String affectedId, String oldVal, String newVal);
}
