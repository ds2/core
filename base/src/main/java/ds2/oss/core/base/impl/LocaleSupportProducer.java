/*
 * Copyright 2012-2013 Dirk Strauss
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
