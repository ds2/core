package ds2.oss.core.api;

import java.util.Date;

/**
 * Created by dstrauss on 20.06.16.
 */
public interface EditableLifeCycleAware extends LifeCycleAware {
    void setValidFrom(Date d);
    void setValidTo(Date d);
}
