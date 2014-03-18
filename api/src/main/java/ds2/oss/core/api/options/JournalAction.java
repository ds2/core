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
package ds2.oss.core.api.options;

/**
 * The journal actions.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum JournalAction {
    /**
     * An option has been created.
     */
    CREATE_OPTION,
    /**
     * An option has been deleted.
     */
    DELETE_OPTION,
    /**
     * An option value has been created.
     */
    CREATE_OPTION_VALUE,
    /**
     * An option value has been authorized.
     */
    AUTHORIZE_OPTION_VALUE,
    /**
     * An option value has been updated.
     */
    UPDATE_OPTION_VALUE,
    /**
     * Deletes an option value.
     */
    DELETE_OPTION_VALUE;
}
