package ds2.oss.core.api.dto.impl;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dstrauss on 19.04.17.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class PagedResult<E> implements Serializable {
    /**
     * Contains the snippet of the result.
     */
    @NotNull
    private List<E> resultList;
    /**
     * Contains the max result size.
     */
    @Min(0)
    private int maxResults;
    /**
     * The page number. Starts with 1.
     */
    @Min(1)
    private int startIndex;
}
