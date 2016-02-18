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
package ds2.oss.core.api.crypto;

/**
 * A dto contract for an encoded content that is based on an IV.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface IvEncodedContent extends EncodedContent {
    /**
     * Returns the init vector that was used to encode the content.
     *
     * @return the init vector
     */
    byte[] getInitVector();
}
