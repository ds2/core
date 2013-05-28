package ds2.oss.core.elasticsearch.test;

import ds2.oss.core.elasticsearch.impl.UseCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Simple usecase test.
 */
public class UseCasesTest extends AbstractInjectionEnvironment {

    private UseCases to;
    private String indexName = "myindex2";

    @BeforeClass
    public void onMethod() {
        to = getInstance(UseCases.class);
    }

    @Test
    public void testCreateIndex() {
        Assert.assertTrue(to.createIndex(indexName));
    }

    @Test(dependsOnMethods = "testCreateIndex")
    public void testDeleteAnythingOfType() {
        // add data

        to.deleteEntriesOfType(indexName, "country");
    }
}
