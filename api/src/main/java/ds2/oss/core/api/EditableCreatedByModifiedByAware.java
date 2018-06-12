package ds2.oss.core.api;

/**
 * Created by dstrauss on 20.06.16.
 */
public interface EditableCreatedByModifiedByAware extends CreatedByModifiedByAware, EditableModifiedAware, EditableCreatedAware {
    /**
     * Sets the creator's username. Usually, using this method implicitely invokes the {@link #setCreated(java.time.LocalDateTime)} method, using the current date.
     *
     * @param s
     */
    void setCreatedBy(String s);

    /**
     * Sets the modifier username. Usually, using this method implicitely invokes the {@link #setModified(java.time.LocalDateTime)} method, using the current date.
     *
     * @param s the modifier's username
     */
    void setModifiedBy(String s);

}
