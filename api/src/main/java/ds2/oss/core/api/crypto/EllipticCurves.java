package ds2.oss.core.api.crypto;

import java.math.BigInteger;
import java.security.spec.EllipticCurve;

/**
 * A set of known safe curves to be used by EC keypair generator.
 * <a href="https://safecurves.cr.yp.to/">https://safecurves.cr.yp.to/</a>
 */
public enum EllipticCurves implements EllipticCurveData {
    M221(new BigInteger("2^221-3"), new BigInteger("117050"), new BigInteger("1"))
    ;
    private BigInteger p;
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;

    /**
     * Creates an enum value representing data for an elliptic curve.
     * @param p the prime
     * @param a the a value
     * @param b the b value
     */
    EllipticCurves(BigInteger p, BigInteger a, BigInteger b){
        this.a=a;
        this.b=b;
        this.p=p;
    }

    @Override
    public BigInteger getP() {
        return p;
    }

    @Override
    public BigInteger getA() {
        return a;
    }

    @Override
    public BigInteger getB() {
        return b;
    }

    @Override
    public BigInteger getC() {
        return c;
    }
}
