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
package ds2.oss.core.statics;

import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreException;

/**
 * Created by dstrauss on 27.03.17.
 */
public interface RequireParam {
    static <E> E requireNotNull(E e, String errorMsg) throws CoreException {
        if (e == null) {
            throw new CoreException(CoreErrors.IllegalArgument, errorMsg);
        }
        return e;
    }

    static String requireNotBlank(String e, String errorMsg) throws CoreException {
        requireNotNull(e, errorMsg);
        if (Methods.isBlank(e)) {
            throw new CoreException(CoreErrors.IllegalArgument, errorMsg);
        }
        return e;
    }

    static void requireTrue(Boolean b, String errorMsg) throws CoreException {
        requireNotNull(b, errorMsg);
        if (!b) {
            throw new CoreException(CoreErrors.IllegalArgument, errorMsg);
        }
    }
}