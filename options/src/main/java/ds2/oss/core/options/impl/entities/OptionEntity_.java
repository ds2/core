package ds2.oss.core.options.impl.entities;

import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.base.impl.db.CreatedModifiedAwareModule;
import ds2.oss.core.base.impl.db.IvEncodedContentModule;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-23T10:51:08.245+0200")
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
