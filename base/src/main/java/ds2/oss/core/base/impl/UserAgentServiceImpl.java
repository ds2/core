package ds2.oss.core.base.impl;

import ds2.oss.core.api.UserAgentDetails;
import ds2.oss.core.api.UserAgentService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserAgentServiceImpl implements UserAgentService {
    @Override
    public UserAgentDetails parseUserAgent(String ua) {
        return null;
    }
}
