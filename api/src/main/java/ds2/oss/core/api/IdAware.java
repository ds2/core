package ds2.oss.core.api;

import java.io.Serializable;

/**
 * Created by dstrauss on 23.03.17.
 */
public interface IdAware<TYPE> extends Serializable {
    TYPE getId();
}
