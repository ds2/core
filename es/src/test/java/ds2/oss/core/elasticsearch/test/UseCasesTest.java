/*
 * Copyright 2012-2015 Dirk Strauss
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
package ds2.oss.core.elasticsearch.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;
import ds2.oss.core.elasticsearch.impl.UseCases;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Simple usecase test.
 *
 * @author dstrauss
 * @version 0.2
 */
public class UseCasesTest extends AbstractInjectionEnvironment {
    /**
     * The test object.
     */
    private UseCases to;
    /**
     * The ES service.
     */
    private ElasticSearchService esSvc;
    /**
     * The index name.
     */
    private final String indexName = "usecasesmyindex2";

    @BeforeClass
    public void onMethod() {
        to = getInstance(UseCases.class);
        esSvc = getInstance(ElasticSearchService.class);
    }

    @AfterClass
    public void afterClass() {
        esSvc.deleteIndexes(indexName);
    }

    @Test
    public void testCreateIndex() {
        Assert.assertTrue(to.createIndex(indexName), "Index could not be created??");
    }

    @Test(dependsOnMethods = "testCreateIndex")
    public void testDeleteAnythingOfType() {
        // add data
        to.deleteEntriesOfType(indexName, "country");
    }
}
