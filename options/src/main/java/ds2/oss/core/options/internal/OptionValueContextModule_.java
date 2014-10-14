package ds2.oss.core.options.internal;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-09T06:44:29.127+0200")
@StaticMetamodel(OptionValueContextModule.class)
public class OptionValueContextModule_ {
	public static volatile SingularAttribute<OptionValueContextModule, String> requestedDomain;
	public static volatile SingularAttribute<OptionValueContextModule, String> serverHostname;
	public static volatile SingularAttribute<OptionValueContextModule, String> serverDomain;
	public static volatile SingularAttribute<OptionValueContextModule, String> serverIp;
	public static volatile SingularAttribute<OptionValueContextModule, RuntimeConfiguration> configuration;
	public static volatile SingularAttribute<OptionValueContextModule, Cluster> cluster;
}
