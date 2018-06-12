package ds2.oss.core.api;

/**
 * Created by dstrauss on 20.06.16.
 */
public interface EditableNormalizedNamedAware extends NormalizedNamedAware {
    void setName(String s);
    void setNormalizedName(String s);
}
