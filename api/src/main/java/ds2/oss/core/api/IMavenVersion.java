/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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

import java.time.LocalDateTime;

/**
 * Specialization of a version: a Maven version.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface IMavenVersion extends Version {
    /**
     * Returns the date of this snapshot version, if available.
     *
     * @return the snapshot date
     */
    LocalDateTime getSnapshotDate();

    /**
     * Checks if this version is a snapshot version.
     *
     * @return TRUE or FALSE
     */
    boolean isSnapshot();
}
