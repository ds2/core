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
/**
 * 
 */
package ds2.oss.core.options.it.test;

import java.lang.invoke.MethodHandles;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.options.it.MyOptions;

/**
 * @author dstrauss
 * 
 */
public class OptionStorageIT extends Arquillian implements MyOptions {
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The test object.
     */
    @Inject
    private NumberedOptionStorageService to;
    
    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar =
            ShrinkWrap.create(JavaArchive.class, "options.jar").addPackages(true, "ds2.oss.core.options")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                // Enable CDI
                // .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
                .addAsManifestResource("my-beans.xml", ArchivePaths.create("beans.xml"));
        return jar;
    }
    
    @Test
    public void testPersist() {
        Option<Long, String> option = to.createOption(USERNAME, "googleUsername");
        LOG.info("Option is {}", option);
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
    }
}
