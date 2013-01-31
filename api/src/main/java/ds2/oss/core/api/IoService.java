/*
 * Copyright 2012 Dirk Strauss
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

import java.util.Properties;

/**
 * The famous IO service to deal with IO operations.
 * 
 * @author dstrauss
 * @version 0.1
 */
public interface IoService {
    /**
     * Loads some properties from the given resource location.
     * 
     * @param resLocation
     *            the resource location
     * @return the loaded properties, or an empty properties object
     */
    Properties loadProperties(String resLocation);
}
