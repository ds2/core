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
package ds2.core.testonly.utils;

import ds2.oss.core.api.ValueGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.Random;

/**
 * Created by deindesign on 12.04.16.
 */
@ApplicationScoped
public class ValueGeneratorImpl implements ValueGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private Random random;
    private static final char[] allowedChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            '/', ' ', ',', '.', '-', '_', '\u00e4', '\u00df'
    };

    @Override
    public String generateString() {
        String rc = null;
        if (random.nextBoolean()) {
            rc = createString(random.nextInt(30) + 5);
        }
        LOG.debug("Returning generated string: {}", rc);
        return rc;
    }

    private String createString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(allowedChars[random.nextInt(allowedChars.length - 1)]);
        }
        return sb.toString();
    }

    @Override
    public int generateIntValue() {
        return random.nextInt();
    }

    @Override
    public long generateLongValue() {
        return random.nextLong();
    }

    @Override
    public boolean generateBoolValue() {
        return random.nextBoolean();
    }
}
