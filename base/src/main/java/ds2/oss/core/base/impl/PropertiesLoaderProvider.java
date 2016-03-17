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
package ds2.oss.core.base.impl;

import ds2.oss.core.api.Validate;
import ds2.oss.core.api.annotations.PathLocation;
import ds2.oss.core.api.annotations.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

/**
 * Created by deindesign on 13.12.15.
 */
@Dependent
public class PropertiesLoaderProvider {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private Validate val;

    /**
     * Produces the path object.
     *
     * @param p
     *            the injection point
     * @return the path, or null if not found
     */
    @Produces
    @PropertiesLoader
    @Dependent
    public Properties createProperties(final InjectionPoint p) {
        Properties rc = null;
        final Set<Annotation> annotations = p.getQualifiers();
        for (Annotation a : annotations) {
            if (a instanceof PropertiesLoader) {
                PropertiesLoader pl = (PropertiesLoader) a;
                rc=readFromFile(pl);
                if(rc==null){
                    rc=readFromSysprop(pl);
                }
                if(rc==null){
                    rc=readFromEnv(pl);
                }
                if(rc==null){
                    rc=readFromResource(pl);
                }
                if(rc==null&&!pl.setNullOnFail()){
                    rc=new Properties();
                }
            }
        }
        LOG.debug("returning properties: {}", rc);
        return rc;
    }

    private Properties readFromResource(PropertiesLoader pl) {
        String resourcePath=pl.resource();
        Properties rc=null;
        if(!val.isEmpty(resourcePath)){
            LOG.debug("Trying to load resource {}", resourcePath);
            try (InputStream is=getClass().getResourceAsStream(resourcePath)){
                rc=new Properties();
                rc.load(is);
            } catch (IOException e) {
                LOG.warn("Error when loading the resource "+resourcePath,e);
                rc=null;
            }

        }
        return rc;
    }

    private Properties readFromEnv(PropertiesLoader pl){
        String envVal=System.getenv(pl.envProp());
        if(!val.isEmpty(envVal)){
            Path p=Paths.get(envVal);
            return readFile(p);
        }
        return null;
    }

    private Properties readFromSysprop(PropertiesLoader pl){
        String sysPropStr=pl.sysProp();
        if(val.isEmpty(sysPropStr)){
            return null;
        }
        String sysPropVal=System.getProperty(pl.sysProp());
        if(!val.isEmpty(sysPropVal)){
            Path p=Paths.get(sysPropVal);
            return readFile(p);
        }
        return null;
    }

    private Properties readFromFile(PropertiesLoader pl){
        String filePath=pl.filePath();
        if(!val.isEmpty(filePath)){
            Path p=Paths.get(filePath);
            return readFile(p);
        }
        return null;
    }

    private Properties readFile(Path p){
            Properties rc=null;
            if(Files.isReadable(p)){
                try(BufferedReader reader=Files.newBufferedReader(p, Charset.forName("utf-8"))) {
                    rc=new Properties();
                    rc.load(reader);
                } catch (IOException e) {
                    LOG.warn("Error when reading the properties file!",e);
                }
            } else {
                LOG.warn("Given file {} is not readable!", p);
            }
            return rc;
    }
}
