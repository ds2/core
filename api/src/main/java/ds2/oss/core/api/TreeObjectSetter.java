package ds2.oss.core.api;

import java.util.Set;

/**
 * Created by dstrauss on 17.03.17.
 */
public interface TreeObjectSetter<E, ID> extends TreeObject<E, ID> {
    void setParent(E newParent);

    void setChildren(Set<E> newSet);
}
