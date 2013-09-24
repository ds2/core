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
 * A basic and simple converter tool.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface ConverterTool {
    /**
     * Converts a given object into int.
     * 
     * @param o
     *            the object
     * @param defValue
     *            the default value, if the conversion failed
     * @return the found value, or the default value
     */
    int toInt(Object o, int defValue);
}
