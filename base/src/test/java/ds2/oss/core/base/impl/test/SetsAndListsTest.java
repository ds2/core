/*
 * Copyright 2012-2015 Dirk Strauss
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
package ds2.oss.core.base.impl.test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Dummy test for sets and lists.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class SetsAndListsTest {
    
    @Test
    public void testMultipleSame() {
        Set<DummyDto> s = new HashSet<>();
        s.add(new DummyDto(1));
        s.add(new DummyDto(1));
        s.add(new DummyDto(1));
        Assert.assertEquals(s.size(), 1);
    }
    
    /**
     * A dummy dto.
     * 
     * @author dstrauss
     * @version 0.3
     */
    public class DummyDto {
        /**
         * the id.
         */
        private int id;
        /**
         * The name.
         */
        private String name;
        
        public DummyDto() {
            // nothing special to do
        }
        
        public DummyDto(final int i) {
            id = i;
        }
        
        public int getId() {
            return id;
        }
        
        public void setId(final int id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        @Override
        public int hashCode() {
            int hash = 5;
            hash = (89 * hash) + id;
            return hash;
        }
        
        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final DummyDto other = (DummyDto) obj;
            if (id != other.id) {
                return false;
            }
            if (!Objects.equals(name, other.name)) {
                return false;
            }
            return true;
        }
        
        @Override
        public String toString() {
            return "DummyDto{" + "id=" + id + ", name=" + name + '}';
        }
        
    }
    
}
