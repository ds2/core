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
package ds2.core.testonly.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;

import ds2.oss.core.api.annotations.PathLocation;

/**
 * Dummy provider for the sec path test.
 *
 * @author dstrauss
 * @version 0.3
 */
@Alternative
@Dependent
@Priority(1000)
public class TestPathLocationProvider {
    /**
     * Produces a path to the maven target directory.
     *
     * @return the test path to use in test cases
     */
    @Produces
    @PathLocation
    public Path createPath() {
        return Paths.get("target", "dummySec");
    }
}
