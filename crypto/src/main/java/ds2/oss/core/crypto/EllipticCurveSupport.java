/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.crypto;

import ds2.oss.core.api.crypto.ECMontgomeryCurveCryptoData;
import ds2.oss.core.api.crypto.WeierstrassBasedCryptoData;
import ds2.oss.core.api.dto.impl.EllipticCurveDataDto;
import ds2.oss.core.api.maths.WeierstrassCurveData;
import ds2.oss.core.math.EllipticCurveMaths;
import jakarta.enterprise.context.Dependent;

import java.math.BigInteger;
import java.security.spec.*;

/**
 * Created by deindesign on 06.04.16.
 */
@Dependent
public class EllipticCurveSupport {
    public ECParameterSpec createFromData(ECMontgomeryCurveCryptoData data) {
        WeierstrassCurveData d = EllipticCurveMaths.toWeierstrass(data);
        EllipticCurveDataDto dto = new EllipticCurveDataDto();
        dto.setPrime(data.getPrime());
        dto.setB(d.getB());
        dto.setA(d.getA());
        dto.setPrimeOrder(data.getPrimeOrder());
        dto.setSuggestedX(data.getSuggestedX());
        return createFromData(dto);
    }

    public ECParameterSpec createFromData(WeierstrassBasedCryptoData d) {
        ECField field = new ECFieldFp(d.getPrime());
        BigInteger a = d.getA();
        BigInteger b = d.getB();
        EllipticCurve curve = new EllipticCurve(field, a, b);
        ECPoint pointOnCurve = new ECPoint(d.getSuggestedX(), EllipticCurveMaths.findOnCurve(a, b, d.getSuggestedX()));
        ECParameterSpec spec = new ECParameterSpec(curve, pointOnCurve, d.getPrimeOrder(), 1);
        return spec;
    }
}
