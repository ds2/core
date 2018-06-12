package ds2.oss.core.api;

import java.util.Set;

/**
 * Created by dstrauss on 23.03.17.
 */
public interface TagsAware<TYPE> {
    Set<TYPE> getTags();
}
