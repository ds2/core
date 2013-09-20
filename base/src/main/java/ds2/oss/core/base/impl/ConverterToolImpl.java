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
