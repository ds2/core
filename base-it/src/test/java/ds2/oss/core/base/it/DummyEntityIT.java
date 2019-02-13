/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.base.it;

import ds2.oss.core.base.itest.DummyEntity;
import ds2.oss.core.base.itest.DummyPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ejb.EJB;

/**
 * A dummy persistence test.
 *
 * @author dstrauss
 * @version 0.2
 */
public class DummyEntityIT extends Arquillian {
    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar =
                ShrinkWrap.create(JavaArchive.class, "example.jar").addPackages(true, "ds2.oss.core.base.itest")
                        .addAsManifestResource("test-persistence.xml", "persistence.xml")
                        // Enable CDI
                        .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        LOG.info(jar.toString(Formatters.VERBOSE));
        return jar;
    }

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DummyEntityIT.class);

    /**
     * The bean to test.
     */
    @EJB
    private DummyPersistence to;

    /**
     * Simple persistence test.
     */
    @Test
    public void testStore1() {
        final DummyEntity e1 = new DummyEntity();
        to.persist(e1);
        Assert.assertNotNull(e1.getId());
    }
}
