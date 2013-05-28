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
package ds2.oss.core.elasticsearch.test;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.test.support.EsNodeGetter;
import ds2.oss.core.elasticsearch.test.support.EsNodeSetter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test for the concurrency system.
 *
 * @author dstrauss
 */
@Test(groups = "concurrency", singleThreaded = true)
public class ConcurrentTest extends AbstractInjectionEnvironment {

  private static final Logger LOG = LoggerFactory.getLogger(ConcurrentTest.class);
  private ElasticSearchNode esNode;

  /**
   */
  public ConcurrentTest() {
    // TODO Auto-generated constructor stub
  }

  @BeforeClass
  public void onClass() {
    esNode = getInstance(ElasticSearchNode.class);
  }

  @Test
  public void testConcurrency() {
    ExecutorService es = Executors.newFixedThreadPool(50);
    for (int i = 0; i < 48; i++) {
      es.submit(new EsNodeGetter(esNode));
    }
    for (int i = 0; i < 2; i++) {
      es.submit(new EsNodeSetter(esNode));
    }
    try {
      Thread.sleep(20000);
    } catch (InterruptedException ex) {
      LOG.debug("Error when sleeping!", ex);
    }
    es.shutdown();
    try {
      es.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException ex) {
      LOG.warn("Terminated executor service!", ex);
    }
    //dummy test, I know ;)
    Assert.assertTrue(true);
  }
}
