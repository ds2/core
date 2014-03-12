package ds2.oss.core.base.impl.test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author dstrauss
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

    public class DummyDto {

        private int id;
        private String name;

        public DummyDto() {
            //nothing special to do
        }

        public DummyDto(int i) {
            id = i;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 89 * hash + this.id;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final DummyDto other = (DummyDto) obj;
            if (this.id != other.id) {
                return false;
            }
            if (!Objects.equals(this.name, other.name)) {
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
