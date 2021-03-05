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
/**
 *
 */
package ds2.oss.core.elasticsearch.api;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IErrorData;

/**
 * @author dstrauss
 *
 */
public class ElasticSearchException extends CoreException {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = -4038016233235930815L;

    /**
     * @param d
     */
    public ElasticSearchException(final IErrorData d) {
        super(d);
    }

    /**
     * @param d
     * @param t
     */
    public ElasticSearchException(final IErrorData d, final Throwable t) {
        super(d, t);
    }

}
