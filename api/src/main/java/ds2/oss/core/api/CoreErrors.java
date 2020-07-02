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
package ds2.oss.core.api;

/**
 * Some common error codes.
 *
 * @author dstrauss
 * @version 0.3
 */
public enum CoreErrors implements IErrorData {

    /**
     * If the encryption failed.
     */
    EncryptionFailed(3),
    /**
     * If json decoding failed.
     */
    JSON_DECODING_FAILED(2),
    /**
     * If json encoding failed.
     */
    JSON_ENCODING_FAILED(1),
    /**
     * If we requested a specific encryption system but the lookup failed for the given type.
     */
    NoEncryptionForType(4),
    /**
     * If a specific required option could not be found.
     */
    OptionNotFound(5),
    /**
     * If a specific generator could not be found.
     */
    NoGeneratorFound(6),
    /**
     * A given argument is not valid.
     */
    IllegalArgument(7),
    /**
     * If a method lock failed.
     */
    LockingFailed(8),
    /**
     * A given algorithm is unknown to the current platform.
     */
    AlgorithmNotFound(9),
    /**
     * If a given provider (security) is unknown.
     */
    UnknownProvider(10),
    /**
     * If a given transport type value could not match with an enum value.
     */
    UnknownEnumValue(11),
    /**
     * If a call or thread timed out or had to be canceled when executed too long.
     */
    TimedOut(12);
    /**
     * The error code.
     */
    private final int code;

    /**
     * Inits the enum value.
     *
     * @param i the numerical error code
     */
    CoreErrors(final int i) {
        code = i;
    }

    @Override
    public int getId() {
        return code;
    }

}
