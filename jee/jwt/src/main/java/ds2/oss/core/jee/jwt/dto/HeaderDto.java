package ds2.oss.core.jee.jwt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class HeaderDto {
    /**
     * the hash algorithm.
     */
    private String alg;
    /**
     * The type of the payload?
     */
    private String typ = "JWT";
    /**
     * The content type. Mime media type.
     */
    private String cty;
}
