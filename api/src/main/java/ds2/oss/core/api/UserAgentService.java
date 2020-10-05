package ds2.oss.core.api;

public interface UserAgentService {
    /**
     * Returns known data about the given user agent. Otherwise null.
     *
     * @param ua the user agent
     * @return the user agent details, or null if non found
     */
    UserAgentDetails parseUserAgent(String ua);
}
