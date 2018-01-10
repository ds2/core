package ds2.oss.core.api.dto.impl;

import ds2.oss.core.api.crypto.HashedResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class HashedResultDto extends IvEncodedContentDto implements HashedResult {
    private String algorithmName;
    private int rounds;
}
