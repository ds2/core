package ds2.oss.core.api.crypto;

import java.math.BigInteger;

/**
 * Created by deindesign on 30.03.16.
 */
public interface EllipticCurveCryptoData extends EllipticCurveData {
    BigInteger getGx();
    BigInteger getGy();

    /**
     * Returns the order of G.
     * @return the order Q
     */
    BigInteger getQ();
}
