package ds2.oss.core.api.environment;

public interface RuntimeEnvironment {
    Cluster getCluster();

    RuntimeConfiguration getRuntimeConfiguration();

    ServerIdentifier getServerIdentifier();
}
