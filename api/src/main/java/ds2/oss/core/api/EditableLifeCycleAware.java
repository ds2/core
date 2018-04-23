package ds2.oss.core.api;

import java.time.LocalDateTime;

/**
 * Created by dstrauss on 20.06.16.
 */
public interface EditableLifeCycleAware extends LifeCycleAware {
    void setValidFrom(LocalDateTime d);

    void setValidTo(LocalDateTime d);
}
