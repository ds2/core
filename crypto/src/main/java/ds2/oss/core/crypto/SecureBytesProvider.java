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

import ds2.oss.core.api.annotations.SecureRandomizer;
import ds2.oss.core.api.crypto.BytesProvider;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.Random;

/**
 * Implementation for some random bytes.
 *
 * @author dstrauss
 * @version 0.3
 */
@Alternative
@ApplicationScoped
@Priority(100)
public class SecureBytesProvider implements BytesProvider {
    /**
     * The randomizer.
     */
    @Inject
    @SecureRandomizer
    private Random zufall;

    @Override
    public byte[] createRandomByteArray(final int size) {
        if (size <= 0) {
            return null;
        }
        final byte[] rc = new byte[size];
        zufall.nextBytes(rc);
        return rc;
    }
}
