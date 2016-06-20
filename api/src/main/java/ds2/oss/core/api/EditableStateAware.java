package ds2.oss.core.api;

/**
 * Created by dstrauss on 19.06.16.
 */
public interface EditableStateAware<E extends EntryState> extends StateAware {
    /**
     * Sets a new state.
     *
     * @param newState the new state
     */
    void setEntryState(E newState);
}
