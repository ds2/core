package ds2.oss.core.base.impl;

import java.util.Collection;

/**
 * Created by dstrauss on 02.10.13.
 */
public class CollectionBuilder<E extends Collection<Z>> {
  private E collection;
  private CollectionBuilder instance;

  private CollectionBuilder(Class<E> colClass) throws IllegalAccessException, InstantiationException {
    collection=colClass.newInstance();
  }
  public static CollectionBuilder create(){
    return null;
  }
}
