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
package ds2.oss.core.options.impl.noop;

import ds2.oss.core.api.options.JournalAction;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionServiceJournal;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * A simple logging journal service.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class LoggingOptionJournalImpl implements OptionServiceJournal {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The scheduler.
     */
    private ScheduledExecutorService scheduler;

    /**
     * Actions to perform at start.
     */
    @PostConstruct
    public void onLoad() {
        LOG.debug("Creating new scheduler");
        scheduler = Executors.newScheduledThreadPool(10);
    }

    /**
     * Actions to perform before end.
     */
    @PreDestroy
    public void onExit() {
        LOG.debug("Shutting down scheduler");
        scheduler.shutdown();
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionServiceJournal#addEntry(java.lang.String,
     * ds2.oss.core.api.options.JournalAction, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public <D, K> void addEntry(final String invoker, final JournalAction action, final K affectedId,
                                final D oldVal, final D newVal) {
        scheduler.execute(new Runnable() {

            @Override
            public void run() {
                LOG.info("{} has taken action {} on id {}, changing {} to {}", new Object[]{invoker, action,
                        affectedId, oldVal, newVal});
            }
        });
    }

    @Override
    public void createdOption(final Option<?, ?> option) {
        scheduler.execute(new Runnable() {

            @Override
            public void run() {
                LOG.info("{} created new option {}", new Object[]{option.getModifiedBy(), option});
            }
        });
    }

}
