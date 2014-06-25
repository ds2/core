/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.api;

/**
 * Some common error codes.
 *
 * @author dstrauss
 */
public enum CoreErrors implements IErrorData {

    /**
     * If json encoding failed.
     */
    JSON_ENCODING_FAILED(1), JSON_DECODING_FAILED(2);
    /**
     * The error code.
     */
    private final int code;

    private CoreErrors(int i) {
        code = i;
    }

    @Override
    public int getId() {
        return code;
    }

}
