package ds2.oss.core.base.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.List;

/**
 * Created by dstrauss on 02.10.13.
 */
public class CollectionBuilder<E extends Collection<Z>,Z> implements CollectionBuilderContract<E,Z> {
  private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  private E collection;
  private boolean allowNull=true;

  private CollectionBuilder(Class<E> colClass){
    try {
      collection=colClass.newInstance();
    } catch (InstantiationException e) {
      LOG.error("Error when instantiating the given collection class!",e);
    } catch (IllegalAccessException e) {
      LOG.error("Cannot access the default constructor of the given collection class!",e);
    }
  }
  private CollectionBuilder(E col){
    collection=col;
  }
  public static <E extends Collection<Z>,Z> CollectionBuilderContract<E,Z> create(Class<E> c){
    CollectionBuilder rc=new CollectionBuilder(c);
    return rc;
  }
  public static <E extends Collection<Z>,Z> CollectionBuilderContract<E,Z> create(E c){
    CollectionBuilder rc=new CollectionBuilder(c);
    return rc;
  }

  @Override
  public CollectionBuilderContract<E, Z> allowNull(boolean b) {
    this.allowNull=b;
    return this;
  }

  @Override
  public CollectionBuilderContract<E, Z> add(Z z) {
    if(allowNull||z!=null){
      collection.add(z);
    }
    return this;
  }

  @Override
  public CollectionBuilderContract<E, Z> addAt(int index, Z z) {
    if(collection instanceof List){
      List<Z> z2= (List<Z>) collection;
      z2.add(index, z);
    } else {
      LOG.warn("Current instance is not a list! Ignoring index.");
      collection.add(z);
    }
    return this;
  }

  @Override
  public CollectionBuilderContract<E, Z> remove(Z z) {
    collection.remove(z);
    return this;
  }

  @Override
  public E build() {
    return collection;
  }
}
