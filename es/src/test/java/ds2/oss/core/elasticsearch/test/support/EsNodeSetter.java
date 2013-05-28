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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.elasticsearch.test.support;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author dstrauss
 */
public class EsNodeSetter implements Callable<Void> {

  private ElasticSearchNode esNode;
  private Random random;

  public EsNodeSetter(ElasticSearchNode e) {
    esNode = e;
    random = new Random();
  }

  @Override
  @SuppressWarnings("SleepWhileInLoop")
  public Void call() throws Exception {
    while (!Thread.currentThread().isInterrupted()) {
      esNode.addTransport(new InetSocketAddress("localhost", random.nextInt(30000) + 1024));
      Thread.sleep(random.nextInt(2500) + 2000);
    }
    return null;
  }
}
