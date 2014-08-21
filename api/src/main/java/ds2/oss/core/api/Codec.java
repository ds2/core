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
 * Dummy contract for any codec.
 *
 * @author dstrauss
 * @version 0.3
 * @param <A> the origin dto type
 * @param <Z> the streaming type
 */
public interface Codec<A, Z> {

    /**
     * Encodes a given object into another one.
     *
     * @param z the original object
     * @return the converted object
     * @throws ds2.oss.core.api.CoreException if an error occurred
     */
    Z encode(A z) throws CoreException;

    /**
     * Decodes a given object.
     *
     * @param a the converted object
     * @return the original object (more or less)
     * @throws ds2.oss.core.api.CoreException if an error occurred
     */
    A decode(Z a) throws CoreException;
    
}
