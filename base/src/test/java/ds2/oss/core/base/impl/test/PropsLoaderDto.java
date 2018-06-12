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
