/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.elasticsearch.test.support;

import java.util.Random;
import java.util.concurrent.Callable;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;

/**
 * The node getter callable.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class EsNodeGetter implements Callable<Void> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(EsNodeGetter.class);
    /**
     * The node.
     */
    private final ElasticSearchNode esNode;
    /**
     * A randomizer.
     */
    private final Random random;
    
    /**
     * INits the callable with the given ES node to access.
     * 
     * @param e
     *            the node to use
     */
    public EsNodeGetter(final ElasticSearchNode e) {
        esNode = e;
        random = new Random();
    }
    
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public Void call() throws Exception {
        while (!Thread.currentThread().isInterrupted()) {
            final Client cl = esNode.get();
            if (cl == null) {
                LOG.error("Client is null!");
            }
            Thread.sleep(random.nextInt(100) + 100);
        }
        return null;
    }
    
}
