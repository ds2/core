package ds2.oss.core.api;

import java.util.Date;

/**
 * Created by dstrauss on 20.06.16.
 */
public interface EditableCreatedByModifiedByAware extends CreatedByModifiedByAware {
    /**
     * Sets the creation date.
     * @param d the creation date of this entity.
     */
    void setCreated(Date d);

    /**
     * Sets the modification date of this entity.
     * @param d the modification date
     */
    void setModified(Date d);

    /**
     * Sets the creator's username. Usually, using this method implicitely invokes the {@link #setCreated(Date)} method, using the current date.
     *
     * @param s
     */
    void setCreatedBy(String s);

    /**
     * Sets the modifier username. Usually, using this method implicitely invokes the {@link #setModified(Date)} method, using the current date.
     *
     * @param s the modifier's username
     */
    void setModifiedBy(String s);
}
