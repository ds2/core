/**
 *
 */
package ds2.oss.core.elasticsearch.test.dto;

import ds2.oss.core.elasticsearch.api.PropertyMapping;
import ds2.oss.core.elasticsearch.api.TypeMapping;

/**
 * @author  dstrauss
 */
@TypeMapping(value = "country")
public class CountryDto {

    @PropertyMapping
    private String name;
    @PropertyMapping
    private String isoCode;

    /**
     */
    public CountryDto() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return  the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param  name  the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return  the isoCode
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * @param  isoCode  the isoCode to set
     */
    public void setIsoCode(final String isoCode) {
        this.isoCode = isoCode;
    }
}
