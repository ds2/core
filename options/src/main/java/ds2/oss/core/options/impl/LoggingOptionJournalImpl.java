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
package ds2.oss.core.options.impl;

import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.options.JournalAction;
import ds2.oss.core.api.options.OptionServiceJournal;

/**
 * A simple logging journal service.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class LoggingOptionJournalImpl implements OptionServiceJournal {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionServiceJournal#addEntry(java.lang.String,
     * ds2.oss.core.api.options.JournalAction, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void addEntry(final String invoker, final JournalAction action, final String affectedId,
        final String oldVal, final String newVal) {
        // TODO Auto-generated method stub
        
    }
    
}
