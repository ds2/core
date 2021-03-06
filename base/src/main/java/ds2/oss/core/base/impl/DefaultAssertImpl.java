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
package ds2.oss.core.base.impl;

import ds2.oss.core.api.AssertHelper;
import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreRuntimeException;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

/**
 * Created by deindesign on 17.03.16.
 */
@Alternative
@Dependent
@Priority(1)
public class DefaultAssertImpl extends AbstractAssertImpl implements AssertHelper {
    @Override
    protected RuntimeException createException(String msg) {
        return new CoreRuntimeException(CoreErrors.IllegalArgument, msg);
    }
}
