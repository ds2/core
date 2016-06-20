package ds2.oss.core.api;

import java.util.Date;

/**
 * Created by dstrauss on 20.06.16.
 */
public interface EditableCreatedAware extends CreatedAware {
    void setCreated(Date d);
}
