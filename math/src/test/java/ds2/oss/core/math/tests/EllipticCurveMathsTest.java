package ds2.oss.core.math.tests;

import ds2.oss.core.math.EllipticCurveMaths;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.spec.EllipticCurve;

/**
 * Created by deindesign on 31.03.16.
 */
public class EllipticCurveMathsTest {
    @Test
    public void testCurveFindY1(){
        BigInteger y=EllipticCurveMaths.findOnCurve(new BigInteger("1"), new BigInteger("2"), new BigInteger("3"));
        Assert.assertEquals(y, new BigInteger("32"));
    }
}
