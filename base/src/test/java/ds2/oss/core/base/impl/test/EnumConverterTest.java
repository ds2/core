/**
 * 
 */
package ds2.oss.core.base.impl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.api.EntryStates;
import ds2.oss.core.base.impl.NumericalEnumConverter;

/**
 * Enum converter test.
 * 
 * @author dstrauss
 * @version 0.1
 */
public class EnumConverterTest {
    /**
     * The test object.
     */
    private final NumericalEnumConverter<EntryStates> to =
        new NumericalEnumConverter<>(EntryStates.class);
    
    /**
     * Inits the test.
     */
    public EnumConverterTest() {
        // TODO Auto-generated constructor stub
    }
    
    @Test
    public void getId() {
        final int v = to.getValue(EntryStates.ACTIVE);
        Assert.assertEquals(v, 1);
    }
    
    @Test
    public void getIdNull() {
        final int v = to.getValue(null);
        Assert.assertEquals(v, -1);
    }
    
    @Test
    public void getEnum() {
        final EntryStates s = to.getEnumByReflection(1, "getById");
        Assert.assertEquals(s, EntryStates.ACTIVE);
    }
    
    @Test
    public void getEnum2() {
        final EntryStates s = to.getEnumByReflection(3, "getById");
        Assert.assertEquals(s, EntryStates.DELETED);
    }
}
