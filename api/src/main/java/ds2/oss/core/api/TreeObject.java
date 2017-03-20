package ds2.oss.core.api;

import java.util.Set;

/**
 * Created by dstrauss on 16.03.17.
 */
public interface TreeObject<E, ID> {
    /**
     * Returns the known children of this leaf. Returns empty set if not children are available.
     *
     * @return a set with the children.
     */
    Set<E> getChildren();

    /**
     * Returns the parent of this leaf.
     *
     * @return
     */
    E getParent();

    ID getParentId();

    /**
     * Returns the id of THIS leaf.
     *
     * @return the id of this tree object.
     */
    ID getTreeObjectId();
}
