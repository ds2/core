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

import ds2.oss.core.api.annotations.SecureRandomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Set;

/**
 * Simple provider for Random instances.
 *
 * @author dstrauss
 * @version 0.3
 */
@Dependent
@Alternative
@Priority(50)
public class RandomProvider {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Creates a simple randomizer.
     *
     * @return a simple randomizer
     * @throws java.security.NoSuchAlgorithmException if an error occurred
     */
    @Produces
    @Alternative
    public Random createSimpleRandom() throws NoSuchAlgorithmException {
        final Random rc = new Random(System.currentTimeMillis());
        return rc;
    }

}
