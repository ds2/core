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

@Generated(value="Dali", date="2014-10-23T10:51:08.285+0200")
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
