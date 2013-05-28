/**
 *
 */
package ds2.oss.core.elasticsearch.test.support;

import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.test.dto.CountryDto;

import java.util.Map;

/**
 * @author  dstrauss
 */
public class CountryCodec implements TypeCodec<CountryDto> {

    /**
     */
    public CountryCodec() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toJson(final CountryDto t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CountryDto toDto(final Map<String, Object> o) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getIndexTypeName() {
        return "country";
    }

    @Override
    public String getIndexName() {
        return null;
    }

    @Override
    public String getMapping() {
        return null;
    }
}
