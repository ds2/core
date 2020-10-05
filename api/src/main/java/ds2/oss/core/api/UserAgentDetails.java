package ds2.oss.core.api;

public interface UserAgentDetails {
    Version getVersion();

    BrowserType getType();

    boolean isMobile();
}
