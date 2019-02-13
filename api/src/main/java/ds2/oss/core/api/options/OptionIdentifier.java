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
package ds2.oss.core.api.options;

import java.io.Serializable;

import ds2.oss.core.api.Descriptive;

/**
 * The keys to uniquely identify a single option.
 *
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the value type
 */
public interface OptionIdentifier<V> extends Serializable, Descriptive {

    /**
     * Returns the application name of this option.
     *
     * @return the application name
     */
    String getApplicationName();

    /**
     * Returns the option name.
     *
     * @return the name of the option
     */
    String getOptionName();

    /**
     * Returns the value type for this option.
     *
     * @return the value type
     */
    ValueType getValueType();

    /**
     * Flag to indicate that this option contains an encrypted value and must be decoded. It is
     * expected that when this value is true, the option by default must have its value already
     * encrypted.
     *
     * @return TRUE if the values for this option must be stored in an encrypted manor, otherwise
     *         and by default FALSE.
     */
    boolean isEncrypted();
}
