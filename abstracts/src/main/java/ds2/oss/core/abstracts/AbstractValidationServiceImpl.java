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
package ds2.oss.core.abstracts;

import ds2.oss.core.api.ValidationService;
import ds2.oss.core.statics.Methods;

/**
 * Basic implementation for the validation service.
 */
public abstract class AbstractValidationServiceImpl<E extends Exception> implements ValidationService<E> {

    @Override
    public <O> O checkNotNull(O obj, String errorMsg) throws E {
        if (obj == null) {
            throw throwException(errorMsg);
        }
        return obj;
    }

    @Override
    public String checkNotEmpty(String s, String errorMsg) throws E {
        if (Methods.isBlank(s)) {
            throw throwException(errorMsg);
        }
        return s;
    }

    protected abstract E throwException(String errorMsg) throws E;
}
