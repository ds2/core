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
/**
 * 
 */
package ds2.oss.core.interceptors;

/**
 * The known supported log levels for now.
 * 
 * @author dstrauss
 * @version 0.1
 */
public enum LogLevel {
    /**
     * The INFO log level.
     */
    INFO,
    /**
     * The WARN log level.
     */
    WARN,
    /**
     * The ERROR log level.
     */
    SEVERE,
    /**
     * The DEBUG log level.
     */
    DEBUG;
}
