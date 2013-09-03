package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.options.OptionIdentifier;

import java.net.URL;
import java.util.List;

/**
 * Some known options.
 */
public enum KnownOptions {
  USERNAME("username",String.class),URL("url", java.net.URL.class),SERVERLIST("list1", List.class)
  ;
  private String optName;
  private Class<?> c;
  private KnownOptions(String name, Class<?> c){
    optName=name;
    this.c=c;
  }

  public OptionIdentifier getIdentifier(){
    return new OptionIdentifier() {
      @Override
      public String getApplicationName() {
        return "MyApp";
      }

      @Override
      public String getOptionName() {
        return optName;
      }

      @Override
      public Class getValueType() {
        return c;
      }
    };
  }
}
