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
package ds2.oss.core.base.impl;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.annotations.PathLocation;

/**
 * The path location provider.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Alternative
public class PathLocationProvider {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * Produces the path object.
     * 
     * @param p
     *            the injection point
     * @return the path, or null if not found
     */
    @Produces
    @PathLocation
    public Path createPath(final InjectionPoint p) {
        Path rc = null;
        final Set<Annotation> annotations = p.getQualifiers();
        for (Annotation a : annotations) {
            if (a instanceof PathLocation) {
                final PathLocation pl = (PathLocation) a;
                String newLoc = null;
                if (pl.environment() != null) {
                    newLoc = System.getenv(pl.environment());
                    LOG.debug("Environment found is {}", newLoc);
                }
                if (pl.property() != null) {
                    newLoc = System.getProperty(pl.property());
                    LOG.debug("property value is {}", newLoc);
                }
                if (newLoc != null) {
                    rc = Paths.get(newLoc);
                }
            }
        }
        LOG.debug("returning found path {}", rc);
        return rc;
    }
}
