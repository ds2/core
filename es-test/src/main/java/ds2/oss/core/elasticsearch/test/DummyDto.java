/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.elasticsearch.test;

import java.util.Set;

import ds2.oss.core.api.es.IndexTypes;
import ds2.oss.core.api.es.PropertyMapping;
import ds2.oss.core.api.es.TypeMapping;

/**
 * A dummy dto.
 * 
 * @author dstrauss
 * @version 0.21
 */
@TypeMapping(value = "dummy")
public class DummyDto {
    /**
     * The name.
     */
    @PropertyMapping(index = IndexTypes.NOT_ANALYZED)
    private String name;
    /**
     * The roles.
     */
    @PropertyMapping(index = IndexTypes.NOT_ANALYZED, indexName = "role")
    private Set<String> roles;
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Set<String> getRoles() {
        return roles;
    }
    
    public void setRoles(final Set<String> roles) {
        this.roles = roles;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DummyDto)) {
            return false;
        }
        
        DummyDto dummyDto = (DummyDto) o;
        
        if (name != null ? !name.equals(dummyDto.name) : dummyDto.name != null) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DummyDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
