package ds2.oss.core.api;

import ds2.oss.core.api.settable.NormalizedNamedDescriptiveAware;

/**
 * Created by dstrauss on 20.06.16.
 */
public interface EditableNormalizedNamedDescriptiveAware extends NormalizedNamedDescriptiveAware {
    void setName(String s);
    void setDescription(String s);
    void setNormalizedName(String s);
}
