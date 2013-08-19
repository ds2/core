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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

/**
 * The test for the LocaleSupport.
 * @author dstrauss
 * @version  0.3
 */
@Test(groups = "localeSupport")
public class LocaleSupportImplTest extends WeldWrapper{
  private LocaleSupportTestProvider to;

  @BeforeClass
  public void onClass(){
    to=getInstance(LocaleSupportTestProvider.class);
  }
  @Test
  public void testHelloNull(){
    Assert.assertEquals(to.getHello(null), "Hello");
  }
  @Test
  public void testHelloGerman(){
    Assert.assertEquals(to.getHello(Locale.GERMANY), "Hallo");
  }
  @Test
  public void testHelloGermanParams(){
    Assert.assertEquals(to.getHelloParam(Locale.GERMANY, "Dirk"), "Hallo, Dirk");
  }
  @Test
  public void testHelloParams(){
    Assert.assertEquals(to.getHelloParam(Locale.US, "Dirk"), "Hello, Dirk");
  }
  @Test
  public void testCurrencyConvertNull(){
    Assert.assertEquals(to.formatCurrency(null,0),"$0.00");
  }
  @Test
  public void testCurrencyConvert1(){
    Assert.assertEquals(to.formatCurrency(Locale.GERMANY,1234.56),"1.234,56 €");
  }
  @Test
  public void testCurrencyConvert2(){
    Assert.assertEquals(to.formatCurrency(Locale.US,1234.56),"$1,234.56");
  }
}
