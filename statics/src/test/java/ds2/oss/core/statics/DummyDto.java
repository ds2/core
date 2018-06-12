package ds2.oss.core.statics;

import ds2.oss.core.api.TreeObjectSetter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dstrauss on 20.03.17.
 */
public class DummyDto implements TreeObjectSetter<DummyDto, String> {
    private Set<DummyDto> children = new HashSet<>();
    private DummyDto parent;
    private String treeObjectId;
    private String parentId;

    public DummyDto(String treeObjectId) {
        this.treeObjectId = treeObjectId;
    }

    public DummyDto(String parent, String treeObjectId) {
        parentId = parent;
        this.treeObjectId = treeObjectId;
    }

    @Override
    public Set<DummyDto> getChildren() {
        return children;
    }

    @Override
    public DummyDto getParent() {
        return parent;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public String getTreeObjectId() {
        return treeObjectId;
    }

    public void setChildren(Set<DummyDto> children) {
        this.children = children;
    }

    public void setParent(DummyDto parent) {
        this.parent = parent;
        if (parent != null) {
            parentId = parent.getTreeObjectId();
        }
    }

    public void setTreeObjectId(String treeObjectId) {
        this.treeObjectId = treeObjectId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DummyDto{");
        sb.append("children=").append(children.size());
        sb.append(", parent=").append(parent);
        sb.append(", treeObjectId='").append(treeObjectId).append('\'');
        sb.append(", parentId='").append(parentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
