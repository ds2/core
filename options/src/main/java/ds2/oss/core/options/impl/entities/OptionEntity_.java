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

import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.db.CreatedModifiedAwareModule;
import ds2.oss.core.base.impl.db.IvEncodedContentModule;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-09T06:44:29.074+0200")
@StaticMetamodel(OptionEntity.class)
public class OptionEntity_ {
	public static volatile SingularAttribute<OptionEntity, Long> id;
	public static volatile SingularAttribute<OptionEntity, String> applicationName;
	public static volatile SingularAttribute<OptionEntity, CreatedModifiedAwareModule> cma;
	public static volatile SingularAttribute<OptionEntity, IvEncodedContentModule> ecm;
	public static volatile SingularAttribute<OptionEntity, String> optionName;
	public static volatile SingularAttribute<OptionEntity, ValueType> valueType;
	public static volatile SingularAttribute<OptionEntity, Boolean> encrypted;
	public static volatile SingularAttribute<OptionEntity, String> defaultValue;
	public static volatile SingularAttribute<OptionEntity, OptionStage> stage;
	public static volatile SingularAttribute<OptionEntity, String> modifierName;
	public static volatile SingularAttribute<OptionEntity, String> description;
}
