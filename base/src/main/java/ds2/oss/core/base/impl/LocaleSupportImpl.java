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

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * The impl.
 *
 * @author dstrauss
 * @version 0.3
 */
public class LocaleSupportImpl implements LocaleSupport {
  private String baseName;

  public LocaleSupportImpl(String bn) {
    this.baseName = bn;
  }

  private Locale parseLocale(Locale locale) {
    if (locale != null) {
      return locale;
    }
    return Locale.US;
  }

  @Override
  public String resolve(String key, Locale loc, Object... params) {
    Locale thisLocale = parseLocale(loc);
    try {
      ResourceBundle.Control ctrl=new ResourceBundle.Control(){
        @Override
        public List<Locale> getCandidateLocales(String baseName, Locale locale) {
          return Arrays.asList(locale,Locale.ROOT);
        }
      };
      ResourceBundle rb = ResourceBundle.getBundle(baseName, thisLocale, getClass().getClassLoader(), ctrl);
      String pattern = rb.getString(key);
      if (params == null || params.length <= 0) {
        return pattern;
      }
      MessageFormat mf2 = new MessageFormat(pattern, thisLocale);
      String rc = mf2.format(params);
      return rc;
    } catch (MissingResourceException e) {
      throw new IllegalStateException("Cannot find resource with base " + baseName, e);
    }
  }

  @Override
  public String formatCurrency(Locale locale, double val) {
    Locale thisLocale = parseLocale(locale);
    NumberFormat nf = NumberFormat.getCurrencyInstance(thisLocale);
    return nf.format(val);
  }
}
