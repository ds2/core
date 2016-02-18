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
package ds2.oss.core.webtools;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * A listener for logback configs.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class LogbackConfigListener implements ServletContextListener {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LogbackConfigListener.class);
    
    /*
     * (non-Javadoc)
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
        final JoranConfigurator jc = new JoranConfigurator();
        jc.setContext(ctx);
        ctx.reset();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("logback.xml")) {
            if (is != null) {
                jc.doConfigure(is);
            }
        } catch (final JoranException ex) {
            LOG.error("Error when reloading the logback config!", ex);
        } catch (final IOException e) {
            LOG.error("Error when loading the logback.xml file from stream!", e);
        } finally {
            StatusPrinter.printInCaseOfErrorsOrWarnings(ctx);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        // ignored
    }
    
}
