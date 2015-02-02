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
import java.util.Date;

/**
 * A persistence contract to identify a persistable object via JPA.
 * 
 * @author dstrauss
 * @version 0.1
 */
public interface CreatedModifiedAware extends Serializable {
    /**
     * Returns the creation date.
     * 
     * @return the creation date
     */
    Date getCreated();
    
    /**
     * Returns the modification date.
     * 
     * @return the modification date
     */
    Date getModified();
}
