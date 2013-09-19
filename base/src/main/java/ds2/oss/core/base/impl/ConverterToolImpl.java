package ds2.oss.core.base.impl;

import ds2.oss.core.api.ConverterTool;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by dstrauss on 19.09.13.
 */
@ApplicationScoped
public class ConverterToolImpl implements ConverterTool{
  @Override
  public int toInt(Object o, int defValue) {
    int rc=defValue;
    if(o!=null){
      if(o instanceof Number){
        Number number= (Number) o;
        rc=number.intValue();
      } else if(o instanceof String){
        rc=Integer.parseInt(o.toString());
      }
    }
    return rc;
  }
}
