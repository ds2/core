/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.math;

import ds2.oss.core.api.dto.impl.EllipticCurveDataDto;
import ds2.oss.core.api.maths.ECMontgomeryCurveData;
import ds2.oss.core.api.maths.WeierstrassCurveData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;

/**
 * Created by deindesign on 31.03.16.
 */
public interface EllipticCurveMaths {
    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Returns y+ for a given point x for an elliptic curve having char(K)!=2,3.
     *
     * @param a the ec value for a
     * @param b the ec value for b
     * @param x the x value to use
     * @return the positive y value of the curve
     */
    static BigInteger findOnCurve(BigInteger a, BigInteger b, BigInteger x) {
        //y=sqrt(x^3+a*x+b);
        BigInteger rc = x.pow(3).add(a.multiply(x)).add(b);
        return rc;
    }

    /**
     * Returns y+ for a given point x for an elliptic curve having char(K)!=2,3.
     *
     * @param a the ec value for a
     * @param b the ec value for b
     * @param c the ec value for c
     * @param x the x value to use
     * @return the positive y value of the curve
     */
    static BigInteger findOnCurve(BigInteger a, BigInteger b, BigInteger c, BigInteger x) {
        //y=sqrt(x^3+a*x^2+b*x+c);
        BigInteger rc = x.pow(3).add(a.multiply(x.pow(2))).add(b.multiply(x)).add(c);
        return rc;
    }

    static WeierstrassCurveData toWeierstrass(ECMontgomeryCurveData c) {
        LOG.debug("Starting to convert given montgomery curve data: {}", c);
        BigInteger a1 = BigInteger.valueOf(3L).subtract(c.getA().pow(2));
        BigInteger a2 = BigInteger.valueOf(3L).multiply(c.getB().pow(2));
        LOG.debug("a1/a2 = {}/{}", a1, a2);
        BigInteger b1 = BigInteger.valueOf(2L).multiply(c.getA().pow(3)).subtract(BigInteger.valueOf(9L).multiply(c.getA()));
        BigInteger b2 = BigInteger.valueOf(27L).multiply(c.getB().pow(3));
        LOG.debug("b1/b2 = {}/{}", b1, b2);
        BigInteger a = a1.divide(a2);
        BigInteger b = b1.divide(b2);
        LOG.debug("So, a and b of weierstrass are {} and {}", a, b);
        EllipticCurveDataDto rc = new EllipticCurveDataDto();
        rc.setA(a);
        rc.setB(b);
        LOG.debug("returning weierstrass data: {}", rc);
        return rc;
    }

    static boolean isMontgomeryCofactor4Curve(ECMontgomeryCurveData d) {
        return false;
    }
}
