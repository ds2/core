package ds2.oss.core.statics;

import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.*;

/**
 * Created by dstrauss on 08.06.16.
 */
public class MethodsTest {
    @Test
    public void testNumber1() {
        assertNull(Methods.parseNumber(null));
    }

    @Test
    public void testNumber2() {
        Number n = Methods.parseNumber("23");
        Assert.assertNotNull(n);
        Assert.assertEquals(n.intValue(), 23);
    }

    @Test
    public void compareStringNulls() {
        Assert.assertEquals(Methods.compare((String) null, (String) null), 0);
    }

    @Test
    public void compareStringNulls1() {
        Assert.assertEquals(Methods.compare("", null), -1);
    }

    @Test
    public void compareStringNulls2() {
        Assert.assertEquals(Methods.compare(null, ""), 1);
    }

    @Test
    public void compareStrings1() {
        Assert.assertEquals(Methods.compare("a", "b"), -1);
    }

    @Test
    public void compareStrings2() {
        Assert.assertEquals(Methods.compare("a", "A"), 0);
    }

    @Test
    public void testIsExceptionOf1() {
        CoreException c1 = new CoreException(CoreErrors.EncryptionFailed);
        assertTrue(Methods.isCausedBy(c1, CoreException.class));
    }

    @Test
    public void testIsExceptionOf2() {
        CoreException c1 = new CoreException(CoreErrors.EncryptionFailed, "test", new SocketTimeoutException("test"));
        assertTrue(Methods.isCausedBy(c1, SocketTimeoutException.class));
    }

    @Test
    public void testIsExceptionOf3() {
        CoreException c1 = new CoreException(CoreErrors.EncryptionFailed, "test", new SocketTimeoutException("test"));
        assertFalse(Methods.isCausedBy(c1, SocketException.class));
    }

    @Test
    public void testShorten1() {
        List<Integer> intList = IntStream.rangeClosed(1, 30).boxed().collect(Collectors.toList());
        Collection<Integer> shortCollection = Methods.shorten(intList, 10);
        assertEquals(shortCollection.size(), 10);
    }

    @Test
    public void testShortenNull() {
        Collection<Integer> shortCollection = Methods.shorten(null, 10);
        assertNull(shortCollection);
    }

}
