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
 * Contract for sec base data.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface SecurityBaseData {
    /**
     * Returns the salt to use.
     * 
     * @return the salt value
     */
    byte[] getSalt();
    
    /**
     * Returns the minimum number of iterations to use to create a hash value.
     * 
     * @return the iteration count
     */
    int getMinIteration();
    
    /**
     * Returns the init vector bytes.
     * 
     * @return the init vector bytes
     */
    byte[] getInitVector();
}