/*
 * Copyright 2012-2015 Dirk Strauss
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
 * Contract for a json codec.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface JsonCodec extends Codec<Object, String> {

    /**
     * Decodes a given json string into the target class.
     *
     * @param z the json string
     * @param c the target class
     * @return the target object
     * @throws JsonCodecException if an error occurred
     */
    <E extends Object> E decode(String z, Class<E> c) throws JsonCodecException;

    <E extends Object> E decodeInto(String z, E instance) throws JsonCodecException;
}
