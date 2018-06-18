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
package ds2.oss.core.base.impl;


import ds2.oss.core.api.annotations.StringLoader;
import ds2.oss.core.statics.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.util.Set;

@Dependent
public class StringPropertyLoaderImpl {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Produces the path object.
     *
     * @param p the injection point
     * @return the path, or null if not found
     */
    @Produces
    @StringLoader
    @Dependent
    public String loadStringVal(final InjectionPoint p) {
        String rc = null;
        final Set<Annotation> annotations = p.getQualifiers();
        for (Annotation a : annotations) {
            if (a instanceof StringLoader) {
                StringLoader pl = (StringLoader) a;
                rc = pl.defaultValue();
                if (!Methods.isBlank(pl.sysProp())) {
                    String sysVal = System.getProperty(pl.sysProp());
                    if (!Methods.isBlank(sysVal)) {
                        rc = sysVal;
                        break;
                    }
                }
                if (!Methods.isBlank(pl.envProp())) {
                    String envVal = System.getenv(pl.envProp());
                    if (!Methods.isBlank(envVal)) {
                        rc = envVal;
                        break;
                    }
                }
                if (pl.setNullOnFail()) {
                    rc = null;
                }

            }
        }
        LOG.debug("returning string value: {}", rc);
        return rc;
    }
}
