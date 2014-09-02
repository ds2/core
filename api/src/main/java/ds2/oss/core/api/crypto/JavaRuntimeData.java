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
package ds2.oss.core.api.crypto;

/**
 * Some details regarding the current java runtime.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface JavaRuntimeData {
    /**
     * Returns the recommended AES keysize of the java runtime. Default is 128 but depending on your
     * runtime, it may also be 256.
     * 
     * @return the AES keysize to use when generating keys
     */
    int getAesMaxKeysize();
}
