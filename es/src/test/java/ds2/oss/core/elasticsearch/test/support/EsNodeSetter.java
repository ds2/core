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
package ds2.oss.core.elasticsearch.test.support;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.Callable;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;

/**
 * The ES node setter.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class EsNodeSetter implements Callable<Void> {
    
    /**
     * THe ES node to use.
     */
    private final ElasticSearchNode esNode;
    /**
     * A randomizer.
     */
    private final Random random;
    
    /**
     * Inits the node setter callable.
     * 
     * @param e
     *            the es node to use
     */
    public EsNodeSetter(final ElasticSearchNode e) {
        esNode = e;
        random = new Random();
    }
    
    @Override
    public Void call() throws Exception {
        while (!Thread.currentThread().isInterrupted()) {
            esNode.addTransport(new InetSocketAddress("localhost", random
                .nextInt(30000) + 1024));
            Thread.sleep(random.nextInt(2500) + 2000);
        }
        return null;
    }
}
