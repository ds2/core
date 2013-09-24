/*
 * Copyright 2012-2013 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.elasticsearch.test.dto;


import ds2.oss.core.elasticsearch.api.annotations.PropertyMapping;
import ds2.oss.core.elasticsearch.api.annotations.TypeMapping;

/**
 * The country dto.
 * 
 * @author dstrauss
 * @version 0.2
 */
@TypeMapping(value = "country")
public class CountryDto {
    
    /**
     * A name.
     */
    @PropertyMapping
    private String name;
    /**
     * An iso code.
     */
    @PropertyMapping
    private String isoCode;
    
    /**
     * returns the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name.
     * 
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }
    
    /**
     * Returns the ISO code.
     * 
     * @return the isoCode
     */
    public String getIsoCode() {
        return isoCode;
    }
    
    /**
     * Sets the ISO code.
     * 
     * @param isoCode
     *            the isoCode to set
     */
    public void setIsoCode(final String isoCode) {
        this.isoCode = isoCode;
    }
}