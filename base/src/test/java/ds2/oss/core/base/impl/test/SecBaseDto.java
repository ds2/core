/*
 * Copyright 2012-2014 Dirk Strauss
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

import java.nio.charset.Charset;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.SecurityBaseData;

/**
 * The dto.
 * 
 * @version 0.2
 * @author dstrauss
 */
@ApplicationScoped
public class SecBaseDto implements SecurityBaseData {
    @Override
    public byte[] getSalt() {
        return "mySaltedWord".getBytes(Charset.forName("utf-8"));
    }
    
    @Override
    public int getMinIteration() {
        return 20000;
    }

  @Override
  public byte[] getInitVector() {
    return new byte[0];
  }
}
