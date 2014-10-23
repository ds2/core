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
package ds2.oss.core.options.impl.entities;

import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.db.CreatedModifiedAwareModule;
import ds2.oss.core.base.impl.db.IvEncodedContentModule;
import ds2.oss.core.base.impl.db.LifeCycleAwareModule;
import ds2.oss.core.options.internal.OptionValueContextModule;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-14T06:16:41.467+0200")
@StaticMetamodel(OptionValueEntity.class)
public class OptionValueEntity_ {
	public static volatile SingularAttribute<OptionValueEntity, String> approverName;
	public static volatile SingularAttribute<OptionValueEntity, String> authorName;
	public static volatile SingularAttribute<OptionValueEntity, CreatedModifiedAwareModule> cma;
	public static volatile SingularAttribute<OptionValueEntity, OptionValueContextModule> ctx;
	public static volatile SingularAttribute<OptionValueEntity, IvEncodedContentModule> ecm;
	public static volatile SingularAttribute<OptionValueEntity, Long> id;
	public static volatile SingularAttribute<OptionValueEntity, LifeCycleAwareModule> lca;
	public static volatile SingularAttribute<OptionValueEntity, OptionEntity> refOption;
	public static volatile SingularAttribute<OptionValueEntity, OptionValueStage> stage;
	public static volatile SingularAttribute<OptionValueEntity, String> value;
	public static volatile SingularAttribute<OptionValueEntity, ValueType> valueType;
}
