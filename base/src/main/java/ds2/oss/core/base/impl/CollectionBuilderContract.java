package ds2.oss.core.base.impl;

import java.util.Collection;

/**
 * Created by dstrauss on 04.10.13.
 */
public interface CollectionBuilderContract<E extends Collection<Z>,Z> {
  CollectionBuilderContract<E,Z> allowNull(boolean b);
  CollectionBuilderContract<E,Z> add(Z z);
  CollectionBuilderContract<E,Z> addAt(int index, Z z);
  CollectionBuilderContract<E,Z> remove(Z z);
  E build();
}
