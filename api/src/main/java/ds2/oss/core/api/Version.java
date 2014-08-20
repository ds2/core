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

import java.io.Serializable;

/**
 * A version.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface Version extends Serializable, Comparable<Version> {
    /**
     * Returns the major part of this version.
     * 
     * @return the major number part
     */
    int getMajorNumber();
    
    /**
     * Returns the minor part of this version.
     * 
     * @return the minor number part
     */
    int getMinorNumber();
    
    /**
     * Returns the patch part of this version.
     * 
     * @return the patch number part
     */
    int getPatchNumber();
}
