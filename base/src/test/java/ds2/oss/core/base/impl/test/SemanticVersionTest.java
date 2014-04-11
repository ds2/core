package ds2.oss.core.base.impl.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.base.impl.SemanticVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Testcases for the semVer.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class SemanticVersionTest {
    /**
     * A logger.
     */
    private static final Logger LOG=LoggerFactory.getLogger(SemanticVersionTest.class);
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNull() {
        SemanticVersion.parse(null);
    }
    
    @Test
    public void testMMP() {
        Assert.assertNotNull(SemanticVersion.parse("1.0.0"));
    }
    
    @Test
    public void testMMP2() {
        final SemanticVersion sv = SemanticVersion.parse("12.1.24");
        Assert.assertNotNull(sv);
        Assert.assertEquals(sv.getMajorNumber(), 12);
        Assert.assertEquals(sv.getMinorNumber(), 1);
        Assert.assertEquals(sv.getPatchNumber(), 24);
        Assert.assertEquals(sv.toString(), "12.1.24");
    }
    
    @Test
    public void testMMPPrerelease1() {
        final SemanticVersion sv = SemanticVersion.parse("12.1.24-rc.1");
        Assert.assertNotNull(sv);
        Assert.assertEquals(sv.toString(), "12.1.24-rc.1");
    }
    
    @Test
    public void testMMPPrerelease2() {
        final SemanticVersion sv = SemanticVersion.parse("12.1.24-pre");
        Assert.assertNotNull(sv);
        Assert.assertEquals(sv.toString(), "12.1.24-pre");
    }
    
    @Test
    public void testMMPPrerelease3() {
        final SemanticVersion sv = SemanticVersion.parse("12.1.24-pre.2");
        Assert.assertNotNull(sv);
        Assert.assertEquals(sv.toString(), "12.1.24-pre.2");
    }
    
    @Test
    public void testOrderingSingle() {
        Assert.assertTrue(SemanticVersion.parse("1.0.0").compareTo(SemanticVersion.parse("1.0.1")) < 0);
    }
    
    @Test
    public void testOrderingSingle2() {
        Assert.assertTrue(SemanticVersion.parse("1.0.0").compareTo(SemanticVersion.parse("1.1.0")) < 0);
    }
    
    @Test
    public void testOrderingSingle3() {
        Assert.assertTrue(SemanticVersion.parse("1.0.0").compareTo(SemanticVersion.parse("2.0.0")) < 0);
    }
    
    @Test
    public void testOrderingSingle4() {
        Assert.assertTrue(SemanticVersion.parse("1.0.0").compareTo(SemanticVersion.parse("1.0.0")) == 0);
    }
    
    @Test
    public void testOrderingSingle5() {
        Assert.assertTrue(SemanticVersion.parse("1.0.0").compareTo(SemanticVersion.parse("0.12.1")) > 0);
    }
    
    @Test
    public void testOrdering1() {
        final List<SemanticVersion> l = new ArrayList<>();
        l.add(SemanticVersion.parse("2.0.0"));
        l.add(SemanticVersion.parse("1.0.1"));
        l.add(SemanticVersion.parse("1.0.0"));
        l.add(SemanticVersion.parse("1.0.2"));
        Collections.sort(l);
        LOG.info("Ordering is {}", l);
        Assert.assertEquals(l.get(0).toString(), "1.0.0");
        Assert.assertEquals(l.get(1).toString(), "1.0.1");
        Assert.assertEquals(l.get(2).toString(), "1.0.2");
        Assert.assertEquals(l.get(3).toString(), "2.0.0");
    }
}
