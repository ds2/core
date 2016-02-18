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

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.SecurityBaseData;

/**
 * Dummy alternative for the sec base data.
 *
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
public class AlternateSecurityBaseDataImpl implements SecurityBaseData {
    
    /**
     * The init vector.
     */
    private byte[] initVector;
    /**
     * A randomizer.
     */
    @Inject
    private Random random;
    /**
     * A randomly generated salt value. On every restart of the component, a new salt value will be
     * generated!
     */
    private byte[] salt;
    
    @Override
    public byte[] getInitVector() {
        return initVector;
    }
    
    @Override
    public int getMinIteration() {
        return 1000;
    }
    
    @Override
    public byte[] getSalt() {
        return salt;
    }
    
    /**
     * Actions to perform after init, after CDI injections.
     */
    @PostConstruct
    public void onClass() {
        byte[] buffer = new byte[512];
        random.nextBytes(buffer);
        salt = buffer;
    }
}
