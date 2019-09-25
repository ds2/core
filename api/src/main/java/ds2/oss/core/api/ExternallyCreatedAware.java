package ds2.oss.core.api;

import java.io.Serializable;
import java.time.ZonedDateTime;

public interface ExternallyCreatedAware extends Serializable {
    ZonedDateTime getExternallyCreatedAt();
}
