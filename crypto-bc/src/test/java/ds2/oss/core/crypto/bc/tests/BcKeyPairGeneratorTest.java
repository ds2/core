package ds2.oss.core.crypto.bc.tests;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.crypto.EllipticCurveNames;
import ds2.oss.core.api.crypto.KeyPairGeneratorService;
import ds2.oss.core.api.crypto.SunEllipticCurveNames;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.security.KeyPair;
import java.util.Enumeration;

/**
 * Created by deindesign on 04.04.16.
 */
public class BcKeyPairGeneratorTest extends AbstractInjectionEnvironment {
    private static final Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private KeyPairGeneratorService to;
    private EllipticCurveNames curves;

    @BeforeClass
    public void onClass(){
        to=getInstance(KeyPairGeneratorService.class);
        curves=getInstance(EllipticCurveNames.class);
        LOG.info("Known curves: {}", curves.getNames());
    }

    @Test(enabled = true)
    public void testECNamed1() throws CoreException {
        KeyPair rc=to.generateEcKey(571, "brainpoolp512t1");
        Assert.assertNotNull(rc);
        Assert.assertNotNull(rc.getPrivate());
        Assert.assertNotNull(rc.getPublic());
    }

    @Test(enabled = true)
    public void testECNamed2() throws CoreException {
        KeyPair rc=to.generateEcKey(571, "prime256v1");
        Assert.assertNotNull(rc);
        Assert.assertNotNull(rc.getPrivate());
        Assert.assertNotNull(rc.getPublic());
    }
}
