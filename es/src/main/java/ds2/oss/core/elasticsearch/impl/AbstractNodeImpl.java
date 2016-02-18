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
package ds2.oss.core.elasticsearch.impl;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PreDestroy;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;

/**
 * An abstract node implementation.
 *
 * @param <T>
 *            The type of the node
 *
 * @author dstrauss
 * @version 0.2
 */
public abstract class AbstractNodeImpl<T extends Client> implements ElasticSearchNode {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractNodeImpl.class);

    /**
     * The node instance.
     */
    protected T client;

    /**
     * A lock.
     */
    protected final Lock lock = new ReentrantLock();

    /**
     * Flag to indicate that a lock is required on the client.
     */
    protected volatile boolean needsLock;

    @Override
    public void addTransport(final InetSocketAddress... isa) {
        needsLock = true;
        try {
            if (!lock.tryLock(10, TimeUnit.SECONDS)) {
                LOG.error("Could not get lock for this node! Cannot add given sockets.");
                return;
            }
        } catch (InterruptedException ex) {
            LOG.error("Could not get lock for this node!", ex);
            return;
        }
        try {
            LOG.info("performing fake addTransport.");
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            LOG.warn("Error when sleeping!", ex);
        } finally {
            lock.unlock();
            needsLock = false;
        }
    }

    @Override
    public Client get() {
        if (!needsLock) {
            return client;
        }
        lock.lock();
        try {
            return client;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Actions to perform on shutdown.
     */
    @PreDestroy
    public void onShutdown() {
        lock.lock();
        try {
            LOG.debug("Shutting down node...");
            client.close();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void removeTransport(final InetSocketAddress... isa) {
        LOG.info("Ignoring");
    }

    /**
     * Waits for green status of the cluster.
     */
    @Override
    public void waitForClusterGreenState() {
        get().admin().cluster().prepareHealth().setWaitForGreenStatus().execute().actionGet();
    }

    /**
     * Waits for the yellow status.
     */
    @Override
    public void waitForClusterYellowState() {
        get().admin().cluster().prepareHealth().setWaitForYellowStatus().execute().actionGet();
    }
}
