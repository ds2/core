/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.api;

/**
 * Any hardware device.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface Device {
    /**
     * Returns the model name of this device.
     *
     * @return the model name
     */
    String getModel();

    /**
     * REturns the revision of this device.
     *
     * @return the revision.
     */
    String getRevision();

    /**
     * Returns the vendor of the device.
     *
     * @return the device's vendor
     */
    String getVendor();
}
