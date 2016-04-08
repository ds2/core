package ds2.oss.core.api.crypto;

import java.math.BigInteger;

/**
 * Created by deindesign on 05.04.16.
 */
public enum MontgomeryCurves implements ECMontgomeryCurveCryptoData{
    /**
     * The montgomery 221 curve.
     *  p=2^221 − 3
     */
    M221(new BigInteger("117050"),new BigInteger("1"),new BigInteger("2").pow(221).subtract(new BigInteger("3")),
            new BigInteger("421249166674228746791672110734682167926895081980396304944335052891"),
            new BigInteger("4")),
    ;
    private BigInteger a;
    private BigInteger b;
    private BigInteger prime;
    private BigInteger primeOrder;
    private BigInteger suggestedX;
    MontgomeryCurves(BigInteger a, BigInteger b, BigInteger p, BigInteger pO, BigInteger x){
        this.a=a;
        this.b=b;
        this.prime=p;
        this.suggestedX=x;
        this.primeOrder=pO;
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
    public BigInteger getPrime() {
        return prime;
    }

    @Override
    public BigInteger getPrimeOrder() {
        return primeOrder;
    }

    @Override
    public BigInteger getSuggestedX() {
        return suggestedX;
    }
}
