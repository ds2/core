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
package ds2.oss.core.crypto.test;

import ds2.oss.core.api.SecurityBaseDataService;
import ds2.oss.core.crypto.SecurityBaseDataServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by dstrauss on 20.09.13.
 */
public class SecurityBaseDataServiceImplTest extends AbstractInjectionEnvironment {
  private SecurityBaseDataService to;
  @BeforeClass
  public void onClass(){
    to=getInstance(SecurityBaseDataService.class);
  }
  @Test
  public void testStoreData(){
    to.storeData(Charset.defaultCharset());
    Path path=Paths.get("target","dummySec");
    Assert.assertTrue(Files.exists(path.resolve("0xsalt.txt")));
  }
}
