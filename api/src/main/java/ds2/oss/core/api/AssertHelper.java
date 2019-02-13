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
package ds2.oss.core.api;

import java.util.Collection;

/**
 * Created by deindesign on 17.03.16.
 */
public interface AssertHelper {
    /**
     * Checks if a given object is not null.
     * @param o the object to test
     * @param errorMsg the error message to crash with
     */
    void assertNotNull(Object o, String errorMsg);

    /**
     * Checks if a given string is not empty/blank.
     * @param s the string to test
     * @param errorMsg the error message to crash with
     */
    void assertNotEmpty(String s, String errorMsg);

    /**
     * Checks if a given collection contains at least one item.
     * @param c the collection to test
     * @param errorMsg the error message to crash with
     */
    void assertNotEmpty(Collection<?> c, String errorMsg);
}
