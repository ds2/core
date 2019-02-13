/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.annotations.PropertiesLoader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

/**
 * Created by deindesign on 16.03.16.
 */
@ApplicationScoped
public class PropsLoaderDto {
    @Inject
    @PropertiesLoader(resource = "/ds2/oss/core/base/impl/test/propsLoader.properties")
    private Properties props;

    public String getValue(){
        return props.getProperty("val");
    }
}
