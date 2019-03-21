package ds2.oss.core.jee.jwt.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
public class HeaderDto {
    private String alg;
    private String typ;
}
