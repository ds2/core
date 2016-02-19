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

import java.io.Serializable;

/**
 * Definition of a db entry state. Usually, you want to have something like ACTIVE, DELETED, LOCKED
 * etc. That is the contract of this interface: it assures that any implementation of this interface
 * has an id and a name.
 * 
 * @author dstrauss
 */
public interface EntryState extends NumericEnumValue, Serializable {
    /**
     * Returns the entry state name.
     * 
     * @return the name of the state
     */
    String getEntryStateName();
}
