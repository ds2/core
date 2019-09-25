package ds2.oss.core.api;

import java.io.Serializable;

public interface ExternalIdAware<PKTYPE> extends Serializable {
    PKTYPE getExternalReferenceId();
}
