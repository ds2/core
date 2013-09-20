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
package ds2.oss.core.base.impl;

import ds2.oss.core.api.PathLocation;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by dstrauss on 20.09.13.
 */
@Alternative
public class PathLocationProvider {
  @Produces
  @PathLocation
  public Path createPath(InjectionPoint p) {
    Path rc = null;
    Set<Annotation> annotations = p.getQualifiers();
    for (Annotation a : annotations) {
      if (a instanceof PathLocation) {
        PathLocation pl = (PathLocation) a;
        String newLoc = null;
        if (pl.environment() != null) {
          newLoc = System.getenv(pl.environment());
        }
        if (pl.property() != null) {
          newLoc = System.getProperty(pl.property());
        }
        if (newLoc != null) {
          rc = Paths.get(newLoc);
        }
      }
    }
    return rc;
  }
}
