package ds2.oss.core.crypto.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.crypto.KeyPairGenAlgorithm;
import ds2.oss.core.api.crypto.KeyPairGeneratorService;
import ds2.oss.core.api.crypto.MontgomeryCurves;
import ds2.oss.core.statics.Securitix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.security.KeyPair;

/**
 * Dummy test for the KP gen service.
 */
public class KeyPairGeneratorTest extends AbstractInjectionEnvironment {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private KeyPairGeneratorService to;

    @BeforeClass
    public void onClass() {
        to = getInstance(KeyPairGeneratorService.class);
        LOG.info("SecProviders: {}", Securitix.getCurrentSecurityProviders());
    }

    @Test
    public void testRsa1() throws CoreException {
        KeyPair rc = to.generate(1024, KeyPairGenAlgorithm.RSA);
        Assert.assertNotNull(rc);
        Assert.assertNotNull(rc.getPrivate());
        Assert.assertNotNull(rc.getPublic());
    }

    @Test(enabled = true)
    public void testEC1() throws CoreException {
        KeyPair rc = to.generate(571, KeyPairGenAlgorithm.EC);
        Assert.assertNotNull(rc);
        Assert.assertNotNull(rc.getPrivate());
        Assert.assertNotNull(rc.getPublic());
    }

    @Test(enabled = false)
    public void testECMontgomery1() throws CoreException {
        KeyPair rc = to.generateEcKey(511, MontgomeryCurves.M221);
        Assert.assertNotNull(rc);
        Assert.assertNotNull(rc.getPrivate());
        Assert.assertNotNull(rc.getPublic());
    }

    @Test(enabled = true)
    public void testDSA() throws CoreException {
        KeyPair rc = to.generate(1024, KeyPairGenAlgorithm.DSA);
        Assert.assertNotNull(rc);
        Assert.assertNotNull(rc.getPrivate());
        Assert.assertNotNull(rc.getPublic());
    }

    @Test(enabled = true)
    public void testDH() throws CoreException {
        KeyPair rc = to.generate(1024, KeyPairGenAlgorithm.DH);
        Assert.assertNotNull(rc);
        Assert.assertNotNull(rc.getPrivate());
        Assert.assertNotNull(rc.getPublic());
    }
}
