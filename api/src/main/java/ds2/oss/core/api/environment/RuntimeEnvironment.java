package ds2.oss.core.api.environment;

public interface RuntimeEnvironment {
    Cluster getCluster();

    RuntimeType getRuntimeType();

    ServerIdentifier getServerIdentifier();
}
