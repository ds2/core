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
package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.LocaleSupport;
import ds2.oss.core.api.annotations.LocaleData;

import javax.inject.Inject;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dstrauss on 08.08.13.
 */
public class LocaleSupportTestProvider {
  @Inject
  @LocaleData(baseName = "ds2/oss/core/base/impl/test/LocTest")
  private LocaleSupport localeSupport;

  public String getHello(Locale locale){
    return localeSupport.resolve("hello",locale);
  }
  public String getHelloParam(Locale locale, String name){
    return localeSupport.resolve("helloParam", locale, name);
  }
  public String formatCurrency(Locale locale, double val){
    return localeSupport.formatCurrency(locale, val);
  }
  public String formatDate(Date date, Locale locale, int dateStyle){
    return localeSupport.formatDate(date, locale, dateStyle);
  }
  public String formatDateTime(Date date, Locale locale, int dateStyle, int timeStyle){
    return localeSupport.formatDateTime(date, locale, dateStyle, timeStyle);
  }
}
