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
package ds2.oss.core.base.impl;

import ds2.oss.core.api.LocaleSupport;
import ds2.oss.core.api.annotations.LocaleData;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import java.lang.annotation.Annotation;

/**
 * A producer for LocaleSupport instances.
 *
 * @author dstrauss
 * @version 0.3
 */
public class LocaleSupportProducer {
    /**
     * Produces a locale support instance.
     *
     * @param p the injection point
     * @return the locale support.
     */
    @Produces
    @LocaleData(baseName = "")
    @Dependent
    public LocaleSupport createLocaleSupport(final InjectionPoint p) {
        String rb = null;
        for (Annotation a : p.getAnnotated().getAnnotations()) {
            if (a instanceof LocaleData) {
                final LocaleData d = (LocaleData) a;
                rb = d.baseName();
                break;
            }
        }
        if (rb == null) {
            throw new IllegalStateException("Injection point does not provide LocaleData to configure!");
        }
        final LocaleSupportImpl rc = new LocaleSupportImpl(rb);
        return rc;
    }
}
