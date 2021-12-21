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

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.crypto.PasswordService;
import ds2.oss.core.statics.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import java.lang.invoke.MethodHandles;

@Dependent
@ds2.oss.core.api.crypto.Argon2
public class Argon2PasswordServiceImpl implements PasswordService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Argon2 argon2;
    private SecurityBaseData baseData;

    @PostConstruct
    public void onCreate() {
        argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i, 128, 128);
        baseData = new SecurityBaseData() {
            @Override
            public byte[] getInitVector() {
                return new byte[0];
            }

            @Override
            public int getMinIteration() {
                return 65535;
            }

            @Override
            public byte[] getSalt() {
                return new byte[0];
            }

            @Override
            public int getCpuCount() {
                return 1;
            }
        };
    }

    @Override
    public String encryptPw(char[] pw) {
        if (pw == null || pw.length <= 0) {
            return null;
        }
        LOG.debug("Will try to encrypt given pw");
        int iterations = 5;
        int memory = 65536;
        int parallel = baseData.getCpuCount();
        String hash = argon2.hash(iterations, memory, parallel, pw);
        LOG.debug("Hashed pw data is: {}", hash);
        return hash;
    }

    @Override
    public boolean isValidPassword(String hash, char[] pw) {
        boolean isValid = false;
        if (!Methods.isBlank(hash) && pw != null) {
            isValid = argon2.verify(hash, pw);
        }
        return isValid;
    }
}
