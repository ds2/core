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
package ds2.oss.core.options.it.test;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;

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

import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.options.it.MyOptions;

/**
 * @author dstrauss
 * 
 */
public class OptionStorageIT extends Arquillian implements MyOptions {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The test object.
     */
    @Inject
    private NumberedOptionStorageService to;
    
    /**
     * Creates the archive to deploy into the glassfish container.
     * 
     * @return the archive to deploy
     */
    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar =
            ShrinkWrap.create(JavaArchive.class, "options.jar").addPackages(true, "ds2.oss.core")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                // Enable CDI
                .addAsManifestResource("my-beans.xml", "beans.xml");
        LOG.info("Content of archive: {}", jar.toString(Formatters.VERBOSE));
        return jar;
    }
    
    @Test
    public void testPersist() {
        Option<Long, String> option = to.createOption(USERNAME, "googleUsername");
        LOG.info("Option is {}", option);
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
    }
    
    @Test
    public void testPersist2() {
        Option<Long, String> option = to.createOption(PW, "secret");
        LOG.info("Option is {}", option);
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
        // Assert.assertNotEquals(option.getDefaultValue(), "secret");
    }
    
    @Test
    public void testPersist3() throws MalformedURLException {
        Option<Long, URL> option = to.createOption(ENDPOINT, new URL("https://my.test.url/endpoint"));
        LOG.info("Option is {}", option);
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
    }
    
    @Test(dependsOnMethods = "testPersist3")
    public void testFindOption1() throws MalformedURLException {
        Option<Long, URL> option = to.getOptionByIdentifier(ENDPOINT);
        Assert.assertNotNull(option);
        Assert.assertEquals(option.getDefaultValue(), new URL("https://my.test.url/endpoint"));
    }
    
    @Test(dependsOnMethods = "testPersist3")
    public void testDeleteOption() {
        Option<Long, URL> foundOption = to.getOptionByIdentifier(ENDPOINT);
        Assert.assertEquals(foundOption.getStage(), OptionStage.Online);
        to.setOptionStage(ENDPOINT, OptionStage.Deleted);
        foundOption = to.getOptionByIdentifier(ENDPOINT);
        Assert.assertEquals(foundOption.getStage(), OptionStage.Deleted);
    }
    
    /*
     * public static void createFileUser(final String userName, final String userPassword, final
     * String userGroups) throws Exception { Server server =
     * Server.getServer(Server.getServerNames().get(0)); String command = "create-file-user";
     * ParameterMap params = new ParameterMap(); params.add("userpassword", userPassword);
     * params.add("groups", userGroups); params.add("username", userName); }
     */
}
