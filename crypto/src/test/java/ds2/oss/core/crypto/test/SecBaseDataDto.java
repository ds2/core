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
/**
 *
 */
package ds2.oss.core.crypto.test;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ds2.oss.core.api.HexCodec;
import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.crypto.SecureRandomizer;

/**
 * @author dstrauss
 */
public class SecBaseDataDto implements SecurityBaseData {
  private byte[] salt;
  @Inject
  private HexCodec hex;


  @PostConstruct
  public void onLoad() {
    salt = hex.decode("c84ea46f99988a39e5b1c3cfbd927dab396c9449651f8ba996469cb6210ff5119f54ab5fc97ec3e33c93d4843ee1f87035309b5db8197c21c1475596fb050bef1168a713e4ccc5f3eacdd4591617dcc4fc526951875bfe4c8d091f7206612c041a65afdee5472f58b4bc5d685389e6c7bdad1098cdb79c1a9330bab31dc6f93d76c8db2fbab63177c56b1112218f7964ffcfc1c24aebd79d2710472a1006bb0503cab97f783ca6e09af66427a74ed52dba1bd9e8b5621a75c47f7f235bd74de225fbd56162c9d1c42db18e1de66590e1694e10ce4a57955814e74f1ea6c32594bfb3f60c56d257bff95198d457bc1b881fb59260370b0183dcadd6e3db1aaad3".toCharArray());
  }

  @Override
  public byte[] getSalt() {
    return salt;
  }

  @Override
  public int getMinIteration() {
    return 65535;
  }

  @Override
  public byte[] getInitVector() {
    return null;
  }

}
