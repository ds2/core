package ds2.oss.core.base.impl;

import ds2.oss.core.api.LocaleSupport;
import ds2.oss.core.api.annotations.LocaleData;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.util.ResourceBundle;

/**
 * Created by dstrauss on 07.08.13.
 */
public class LocaleSupportProducer {
  @Produces
  public LocaleSupport createLocaleSupport(InjectionPoint p){
    String rb=null;
    for(Annotation a:p.getAnnotated().getAnnotations()){
      if(a instanceof LocaleData){
        LocaleData d= (LocaleData) a;
        rb=d.baseName();
        break;
      }
    }
    if(rb==null){
      throw new IllegalStateException("Injection point does not provide LocaleData to configure!");
    }
    LocaleSupportImpl rc=new LocaleSupportImpl(rb);
    return rc;
  }
}
