package ds2.oss.core.base.impl;

import ds2.oss.core.api.SystemPropertyService;

import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;

/**
 * Created by deindesign on 08.04.16.
 */
@ApplicationScoped
public class SystemPropertyServiceImpl implements SystemPropertyService {
    @Override
    public Properties getAllSysProps() {
        return System.getProperties();
    }
}
