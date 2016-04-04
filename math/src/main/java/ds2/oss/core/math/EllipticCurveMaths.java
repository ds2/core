package ds2.oss.core.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.spec.EllipticCurve;

/**
 * Created by deindesign on 31.03.16.
 */
public interface EllipticCurveMaths {
    Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Returns y+ for a given point x for an elliptic curve having char(K)!=2,3.
     * @param a the ec value for a
     * @param b the ec value for b
     * @param x the x value to use
     * @return the positive y value of the curve
     */
    static BigInteger findOnCurve(BigInteger a, BigInteger b, BigInteger x){
        //y=sqrt(x^3+a*x+b);
        BigInteger rc=x.pow(3).add(a.multiply(x)).add(b);
        return rc;
    }

    /**
     * Returns y+ for a given point x for an elliptic curve having char(K)!=2,3.
     * @param a the ec value for a
     * @param b the ec value for b
     * @param c the ec value for c
     * @param x the x value to use
     * @return the positive y value of the curve
     */
    static BigInteger findOnCurve(BigInteger a, BigInteger b, BigInteger c, BigInteger x){
        //y=sqrt(x^3+a*x^2+b*x+c);
        BigInteger rc=x.pow(3).add(a.multiply(x.pow(2))).add(b.multiply(x)).add(c);
        return rc;
    }
}
