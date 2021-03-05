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
package ds2.oss.core.dbtools.it;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.dbtools.it.entities.MyEntity;
import ds2.oss.core.dbtools.it.entities.StateEntity;

/**
 * Created by dstrauss on 19.06.15.
 */
public class DbToolsIT extends Arquillian {
    private static final Logger LOG = LoggerFactory.getLogger(DbToolsIT.class);
    
    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "dbtools.jar").addPackages(true, "ds2.oss.core")
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
            // Enable CDI
            .addAsManifestResource("my-beans.xml", "beans.xml")
        ;
        LOG.info("Content of archive: {}", jar.toString(Formatters.VERBOSE));
        return jar;
    }
    
    /**
     * The test object.
     */
    @Inject
    private MyEntityService to;
    @Inject
    private StateService states;
    
    @Test
    public void createStates() {
        Assert.assertNotNull(states);
        Assert.assertNotNull(states.createStateByName("prepared"));
        Assert.assertNotNull(states.createStateByName("allowed"));
        Assert.assertNotNull(states.createStateByName("extended"));
        Assert.assertNotNull(states.createStateByName("flying"));
        Assert.assertNotNull(states.createStateByName("running"));
        Assert.assertNotNull(states.createStateByName("basically active"));
        Assert.assertNotNull(states.createStateByName("falling"));
        Assert.assertNotNull(states.createStateByName("crushing"));
        Assert.assertNotNull(states.createStateByName("locked"));
        Assert.assertNotNull(states.createStateByName("deleted"));
    }
    
    @Test(dependsOnMethods = "createStates")
    public void testCreate1() {
        MyEntity e = to.create("test", new StateEntity(4, "flying high"));
        Assert.assertNotNull(e);
        Assert.assertNotNull(e.getEntryState());
        Assert.assertEquals(e.getEntryState().getNumericalValue(), 4);
    }
}
