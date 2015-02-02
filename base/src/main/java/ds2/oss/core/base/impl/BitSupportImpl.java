/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.base.impl;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.BitSupport;

/**
 * The impl for the bit support contract.
 *
 * @author dstrauss
 * @version 0.1
 */
@ApplicationScoped
public class BitSupportImpl implements BitSupport {
    /**
     * Constant for the number 8.
     */
    private static final int N8 = 8;
    /**
     * Constant for 0xff byte sequence.
     */
    private static final int XFF = 0xff;

    /**
     * Inits the bean.
     */
    public BitSupportImpl() {
        // nothing special to do
    }

    @Override
    public int createInt(final byte... b) {
        int rc = 0;
        if (b == null) {
            return rc;
        }
        for (byte by : b) {
            rc <<= N8;
            rc |= by & XFF;
        }
        return rc;
    }

    @Override
    public long createLong(final byte... b) {
        long rc = 0;
        if (b == null) {
            return rc;
        }
        for (byte by : b) {
            rc <<= N8;
            rc |= by & XFF;
        }
        return rc;
    }

    @Override
    public byte[] getBytesFrom(final long l, final int offset, final int length) {
        // TBD
        return null;
    }

}
