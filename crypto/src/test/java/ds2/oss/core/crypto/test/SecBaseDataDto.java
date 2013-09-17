/*
 * Copyright 2012-2013 Dirk Strauss
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
/**
 * 
 */
package ds2.oss.core.crypto.test;

import java.util.Random;

import javax.inject.Inject;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.crypto.SecureRandomizer;

/**
 * @author dstrauss
 * 
 */
public class SecBaseDataDto implements SecurityBaseData {
    @Inject
    @SecureRandomizer
    private Random r;
    private byte[] salt;
    
    public void onLoad() {
    }
    
    @Override
    public byte[] getSalt() {
        return salt;
    }
    
    @Override
    public int getMinIteration() {
        return 65535;
    }
    
    @Override
    public byte[] getInitVector() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
