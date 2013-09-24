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
package ds2.oss.core.api;

/**
 * A helper service to convert enums to ints, and vice versa.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the enum type
 */
public interface EnumPersistenceSupport<E extends Enum<E>> {
    /**
     * Converts the given enum value into an int value.
     * 
     * @param e
     *            the enum value
     * @return the int value
     */
    int toInt(E e);
    
    /**
     * Converts a given int value into an enum value.
     * 
     * @param e
     *            the int value
     * @return the enum value, or null if not found
     */
    E toValue(int e);
}