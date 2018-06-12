package ds2.oss.core.api.dto.impl;

import ds2.oss.core.api.OperatingSystemDetails;
import ds2.oss.core.api.OperatingSystemNames;
import ds2.oss.core.api.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by dstrauss on 21.03.17.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OperatingSystemDetailsDto implements OperatingSystemDetails {
    private OperatingSystemNames systemName;
    private Version version;
    private int bitSize;

    @Override
    public int compareTo(OperatingSystemDetails o) {
        int rc = systemName.compareTo(o.getSystemName());
        if (rc == 0) {
            rc = version.compareTo(o.getVersion());
        }
        if (rc == 0) {
            rc = bitSize - o.getBitSize();
        }
        return rc;
    }
}
