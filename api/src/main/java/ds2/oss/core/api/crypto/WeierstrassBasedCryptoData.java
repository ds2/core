package ds2.oss.core.api.crypto;

import ds2.oss.core.api.maths.WeierstrassCurveData;

import java.math.BigInteger;

/**
 * Created by deindesign on 05.04.16.
 */
public interface WeierstrassBasedCryptoData extends WeierstrassCurveData {
    BigInteger getPrime();
    BigInteger getSuggestedX();
    BigInteger getPrimeOrder();
}
