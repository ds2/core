package ds2.oss.core.api;

import java.time.ZoneId;

public interface User<PKTYPE> extends IdAware<PKTYPE>, NamedAware, CreatedByModifiedByAware, StateAware {

    String getFirstName();

    String getLastName();

    /**
     * Returns the zone id of the user. From the zoneId, you can create time based data.
     *
     * @return the zone id of the user
     */
    ZoneId getUserTimeZone();
}
