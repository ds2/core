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
package ds2.oss.core.api.dto.impl;

import ds2.oss.core.api.crypto.EllipticCurveData;

import java.math.BigInteger;

/**
 * Created by deindesign on 05.04.16.
 */
public class EllipticCurveDataDto implements EllipticCurveData {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger primeOrder;
    private BigInteger prime;
    private BigInteger suggestedX;

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

    public void setA(BigInteger a) {
        this.a = a;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public void setC(BigInteger c) {
        this.c = c;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EllipticCurveDataDto{");
        sb.append("p=").append(prime);
        sb.append(", a=").append(a);
        sb.append(", b=").append(b);
        sb.append(", c=").append(c);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public BigInteger getPrime() {
        return prime;
    }

    @Override
    public BigInteger getSuggestedX() {
        return suggestedX;
    }

    @Override
    public BigInteger getPrimeOrder() {
        return primeOrder;
    }

    public void setPrimeOrder(BigInteger primeOrder) {
        this.primeOrder = primeOrder;
    }

    public void setPrime(BigInteger prime) {
        this.prime = prime;
    }

    public void setSuggestedX(BigInteger suggestedX) {
        this.suggestedX = suggestedX;
    }
}

