package ds2.oss.core.api.crypto;

import java.math.BigInteger;

/**
 * Definition of a single elliptic curve using integer space instead of R.
 * @author dstrauss
 */
public interface EllipticCurveData extends WeierstrassBasedCryptoData {

    /**
     * For curves with char(K)=3 returns the value of c.
     * @return the c value for curves with characteristic 3.
     */
    BigInteger getC();
}
