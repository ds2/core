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
package ds2.oss.core.crypto.test;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.enterprise.inject.Produces;

import ds2.oss.core.api.PathLocation;

/**
 * Dummy provider for the sec path test.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class PathLocationProvider {
    @Produces
    @PathLocation
    public Path createPath() {
        return Paths.get("target", "dummySec");
    }
}
