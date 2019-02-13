/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.base.impl;

import ds2.oss.core.api.AssertHelper;

import java.util.Collection;

/**
 * Created by deindesign on 17.03.16.
 */
public abstract class AbstractAssertImpl implements AssertHelper {
    protected abstract RuntimeException createException(String msg);

    @Override
    public void assertNotNull(Object o, String errorMsg) {
        if(o==null){
            throw createException(errorMsg);
        }
    }

    @Override
    public void assertNotEmpty(String s, String errorMsg) {
        if(s==null||s.trim().length()==0){
            throw createException(errorMsg);
        }
    }

    @Override
    public void assertNotEmpty(Collection<?> c, String errorMsg) {
        if(c==null||c.isEmpty()){
            throw createException(errorMsg);
        }
    }
}
