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
package ds2.oss.core.options.it.test;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import javax.inject.Inject;

import ds2.oss.core.api.environment.RuntimeType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.api.dto.impl.OptionValueContextDto;
import ds2.oss.core.api.environment.Clusters;
import ds2.oss.core.api.environment.ServerIdentifierDto;
import ds2.oss.core.api.options.CreateOptionException;
import ds2.oss.core.api.options.CreateOptionValueException;
import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionException;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.options.it.MyOptions;

/**
 * @author dstrauss
 * 
 */
public class OptionStorageIT extends Arquillian implements MyOptions {
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
    
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * The test object.
     */
    @Inject
    private NumberedOptionStorageService to;
    
    @Test(dependsOnMethods = "testPersist")
    public void testContextValueCheck() throws CreateOptionValueException {
        OptionValue<Long, String> optionValue1 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(), null, "myUsername1");
        OptionValue<Long, String> optionValue2 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A), null, "myUsername2");
        Assert.assertNotNull(optionValue1);
        Assert.assertNotNull(optionValue2);
        Assert.assertNotEquals(optionValue1, optionValue2);
        to.approveOptionValue(optionValue1.getId());
        to.approveOptionValue(optionValue2.getId());
        OptionValue<Long, String> ov1 = to.findBestOptionValueByContext(USERNAME, null);
        Assert.assertNotNull(ov1);
        Assert.assertEquals(ov1.getValue(), "myUsername1");
        OptionValue<Long, String> ov2 =
            to.findBestOptionValueByContext(USERNAME, new OptionValueContextDto(Clusters.A));
        Assert.assertNotNull(ov2);
        Assert.assertEquals(ov2.getValue(), "myUsername2");
    }
    
    @Test(dependsOnMethods = "testPersist")
    public void testContextValueCheckLevel2() throws CreateOptionValueException {
        OptionValue<Long, String> optionValue1 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A, RuntimeType.Staging), null,
                "myUsernameAStaging");
        OptionValue<Long, String> optionValue2 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A, RuntimeType.Live), null,
                "myUsernameALive");
        Assert.assertNotNull(optionValue1);
        Assert.assertNotNull(optionValue2);
        Assert.assertNotEquals(optionValue1, optionValue2);
        to.approveOptionValue(optionValue1.getId());
        to.approveOptionValue(optionValue2.getId());
        OptionValue<Long, String> ov2 =
            to.findBestOptionValueByContext(USERNAME, new OptionValueContextDto(Clusters.A,
                RuntimeType.Staging));
        Assert.assertNotNull(ov2);
        Assert.assertEquals(ov2.getValue(), "myUsernameAStaging");
        ov2 =
            to.findBestOptionValueByContext(USERNAME, new OptionValueContextDto(Clusters.A, RuntimeType.Live));
        Assert.assertNotNull(ov2);
        Assert.assertEquals(ov2.getValue(), "myUsernameALive");
    }
    
    @Test(dependsOnMethods = "testPersist")
    public void testContextValueCheckLevel3() throws CreateOptionValueException {
        OptionValue<Long, String> optionValue1 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A, RuntimeType.Staging,
                "test.domain"), null, "myUsernameAStagingTestDomain");
        OptionValue<Long, String> optionValue2 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A, RuntimeType.Staging,
                "myflower.domain"), null, "myUsernameAStagingFlowerDomain");
        Assert.assertNotNull(optionValue1);
        Assert.assertNotNull(optionValue2);
        Assert.assertNotEquals(optionValue1, optionValue2);
        to.approveOptionValue(optionValue1.getId());
        to.approveOptionValue(optionValue2.getId());
        OptionValue<Long, String> ov2 =
            to.findBestOptionValueByContext(USERNAME, new OptionValueContextDto(Clusters.A,
                RuntimeType.Staging, "test.domain"));
        Assert.assertNotNull(ov2);
        Assert.assertEquals(ov2.getValue(), "myUsernameAStagingTestDomain");
        ov2 =
            to.findBestOptionValueByContext(USERNAME, new OptionValueContextDto(Clusters.A,
                RuntimeType.Staging, "myflower.domain"));
        Assert.assertNotNull(ov2);
        Assert.assertEquals(ov2.getValue(), "myUsernameAStagingFlowerDomain");
    }
    
    /**
     * Tests for level 4 queries on the options framework.
     * 
     * @throws CreateOptionValueException
     *             if an error occurred
     */
    @Test(dependsOnMethods = "testPersist")
    public void testContextValueCheckLevel4() throws CreateOptionValueException {
        OptionValue<Long, String> optionValue1 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A, RuntimeType.Staging,
                "test.domain", new ServerIdentifierDto("localhost")), null, "myUsernameAStagingTestDomainLocalhost");
        OptionValue<Long, String> optionValue2 =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A, RuntimeType.Staging,
                "test.domain", new ServerIdentifierDto("staging.ds2")), null, "myUsernameAStagingTestDomainStaging");
        Assert.assertNotNull(optionValue1);
        Assert.assertNotNull(optionValue2);
        Assert.assertNotEquals(optionValue1, optionValue2);
        to.approveOptionValue(optionValue1.getId());
        to.approveOptionValue(optionValue2.getId());
        OptionValue<Long, String> ov2 =
            to.findBestOptionValueByContext(USERNAME, new OptionValueContextDto(Clusters.A,
                RuntimeType.Staging, "test.domain", new ServerIdentifierDto("localhost")));
        Assert.assertNotNull(ov2);
        Assert.assertEquals(ov2.getValue(), "myUsernameAStagingTestDomainLocalhost");
        ov2 =
            to.findBestOptionValueByContext(USERNAME, new OptionValueContextDto(Clusters.A,
                RuntimeType.Staging, "test.domain", new ServerIdentifierDto("staging.ds2")));
        Assert.assertNotNull(ov2);
        Assert.assertEquals(ov2.getValue(), "myUsernameAStagingTestDomainStaging");
    }
    
    /**
     * Test to create a simple option value.
     * 
     * @throws CreateOptionValueException
     *             if an error occurred
     */
    @Test(dependsOnMethods = "testPersist")
    public void testCreateOptionValue1() throws CreateOptionValueException {
        OptionValue<Long, String> optionValue =
            to.createOptionValue(USERNAME, new OptionValueContextDto(Clusters.A), null, "pascal");
        Assert.assertNotNull(optionValue);
        Assert.assertNotNull(optionValue.getId());
        Assert.assertEquals(optionValue.getValue(), "pascal");
        Assert.assertEquals(optionValue.getStage(), OptionValueStage.Prepared);
        LOG.info("OV is {}", optionValue);
    }
    
    /**
     * Test to create a simple option value.
     * 
     * @throws CreateOptionValueException
     *             if an error occurred
     * @throws OptionException
     *             if an option error occurred
     */
    @Test(dependsOnMethods = "testSecurePersist1")
    public void testCreateOptionValueSecure() throws CreateOptionValueException, OptionException {
        OptionValue<Long, String> optionValue =
            to.createOptionValue(PW, new OptionValueContextDto(Clusters.A), null, "mysecret");
        Assert.assertNotNull(optionValue);
        Assert.assertNotNull(optionValue.getId());
        Assert.assertTrue(optionValue.isEncrypted());
        Assert.assertNull(optionValue.getValue());
        Assert.assertNotNull(optionValue.getEncoded());
        Assert.assertNotNull(optionValue.getInitVector());
        Assert.assertEquals(optionValue.getStage(), OptionValueStage.Prepared);
        Assert.assertEquals(optionValue.getUnencryptedValue(), "mysecret");
        to.approveOptionValue(optionValue.getId());
        optionValue = to.getOptionValueById(optionValue.getId());
        Assert.assertNotNull(optionValue);
        Assert.assertEquals(optionValue.getStage(), OptionValueStage.Live);
    }
    
    @Test(dependsOnMethods = "testUrlPersist")
    public void testDeleteOption() throws OptionException {
        Option<Long, URL> foundOption = to.getOptionByIdentifier(ENDPOINT);
        Assert.assertEquals(foundOption.getStage(), OptionStage.Online);
        to.setOptionStage(ENDPOINT, OptionStage.Deleted);
        foundOption = to.getOptionByIdentifier(ENDPOINT);
        Assert.assertEquals(foundOption.getStage(), OptionStage.Deleted);
    }
    
    @Test(dependsOnMethods = "testCreateOptionValueSecure")
    public void testFindBestOptionValue1() throws CreateOptionValueException {
        OptionValue<Long, String> optionValue =
            to.findBestOptionValueByContext(PW, new OptionValueContextDto(Clusters.A));
        Assert.assertNotNull(optionValue);
        Assert.assertNotNull(optionValue.getId());
        Assert.assertTrue(optionValue.isEncrypted());
        Assert.assertEquals(optionValue.getUnencryptedValue(), "mysecret");
    }
    
    @Test(dependsOnMethods = "testCreateOptionValueSecure")
    public void testFindBestOptionValue2() throws CreateOptionValueException {
        OptionValue<Long, String> optionValue =
            to.findBestOptionValueByContext(PW, new OptionValueContextDto(Clusters.B));
        Assert.assertNull(optionValue);
    }
    
    @Test(dependsOnMethods = "testUrlPersist")
    public void testFindOption1() throws MalformedURLException, OptionException {
        Option<Long, URL> option = to.getOptionByIdentifier(ENDPOINT);
        Assert.assertNotNull(option);
        Assert.assertEquals(option.getDefaultValue(), new URL("https://my.test.url/endpoint"));
    }
    
    /**
     * Test to check if we can recover an encryption option.
     * 
     * @throws OptionException
     *             if an error occurred
     * 
     */
    @Test(dependsOnMethods = "testSecurePersist1")
    public void testFindSecureOption1() throws OptionException {
        Option<Long, String> option = to.getOptionByIdentifier(PW);
        Assert.assertNotNull(option);
        Assert.assertNull(option.getDefaultValue());
        Assert.assertEquals(option.getDecryptedValue(), "secret");
    }
    
    /**
     * Test for single string persistence.
     * 
     * @throws CreateOptionException
     *             if an error occurred
     */
    @Test
    public void testPersist() throws CreateOptionException {
        Option<Long, String> option = to.createOption(USERNAME, "googleUsername");
        LOG.info("Option is {}", option);
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
    }
    
    public void testScheduleOptionValue() throws OptionException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        OptionValue<Long, String> generatedOptionValue =
            to.createOptionValue(USERNAME, null, cal.getTime(), "myFutureUsername");
        Assert.assertNotNull(generatedOptionValue);
        Assert.assertNotNull(generatedOptionValue.getId());
        Assert.assertEquals(generatedOptionValue.getStage(), OptionValueStage.Prepared);
        to.approveOptionValue(generatedOptionValue.getId());
        OptionValue<Long, String> ov = to.<String> getOptionValueById(generatedOptionValue.getId());
        Assert.assertNotNull(ov);
        Assert.assertEquals(ov.getStage(), OptionValueStage.Prepared);
    }
    
    /**
     * Test for the secure string option.
     * 
     * @throws CreateOptionException
     *             if an error occurred
     */
    @Test
    public void testSecurePersist1() throws CreateOptionException {
        Option<Long, String> option = to.createOption(PW, "secret");
        LOG.info("Option is {}", option);
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
        // encrypted options do not have a default value
        Assert.assertNull(option.getDefaultValue());
        // the original value must be here
        Assert.assertEquals(option.getDecryptedValue(), "secret");
        // there must be some encoded stuff
        Assert.assertNotNull(option.getEncoded());
        Assert.assertNotNull(option.getInitVector());
        // using toString(), no password must be printed
        Assert.assertFalse(option.toString().contains("secret"));
    }
    
    /**
     * Test for a url option.
     * 
     * @throws MalformedURLException
     *             on url error
     * @throws CreateOptionException
     *             on option create error
     */
    @Test
    public void testUrlPersist() throws MalformedURLException, CreateOptionException {
        Option<Long, URL> option = to.createOption(ENDPOINT, new URL("https://my.test.url/endpoint"));
        LOG.info("Option is {}", option);
        Assert.assertNotNull(option);
        Assert.assertNotNull(option.getId());
    }
    
    /*
     * public static void createFileUser(final String userName, final String userPassword, final
     * String userGroups) throws Exception { Server server =
     * Server.getServer(Server.getServerNames().get(0)); String command = "create-file-user";
     * ParameterMap params = new ParameterMap(); params.add("userpassword", userPassword);
     * params.add("groups", userGroups); params.add("username", userName); }
     */
}
