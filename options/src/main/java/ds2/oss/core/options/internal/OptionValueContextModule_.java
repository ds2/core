/*
 * Copyright 2012-2014 Dirk Strauss
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
