package ds2.oss.core.api.crypto;

import java.math.BigInteger;

/**
 * Created by deindesign on 05.04.16.
 */
public interface ECMontgomeryCurveCryptoData extends ds2.oss.core.api.maths.ECMontgomeryCurveData {
    BigInteger getPrime();
    BigInteger getPrimeOrder();
    BigInteger getSuggestedX();
}
