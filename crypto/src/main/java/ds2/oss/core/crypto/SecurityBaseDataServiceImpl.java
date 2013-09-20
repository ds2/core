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
package ds2.oss.core.crypto;

import ds2.oss.core.api.*;
import ds2.oss.core.api.crypto.BytesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * The impl.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class SecurityBaseDataServiceImpl implements SecurityBaseDataService {
  private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  private static final String SALT_FILENAME="0xsalt.txt";
  private static final String IV_FILENAME="0xiv.txt";
  private static final String PROPS_FILENAME="sec.properties";
  public static final String SYS_PROPERTY="ds2.app.sec.home";
  @Inject
  private HexCodec hex;
  @Inject
  private IoService io;
  @Inject
  private ConverterTool conv;
  private byte[] salt;
  private byte[] initVector;
  private int minIteration = 65535;
  @Inject
  private BytesProvider bytes;
  @Inject
  @PathLocation(property = SYS_PROPERTY, environment = "DS2_APPSEC_HOME")
  private Path storageLocation;

  @PostConstruct
  public void onLoad() {
    if(storageLocation==null){
      storageLocation= Paths.get(System.getProperty("user.home"),".ds2AppSec");
    }
    LOG.debug("Location to use is {}", storageLocation);
    Path saltFile =storageLocation.resolve(SALT_FILENAME) ;
    Path ivFile = storageLocation.resolve(IV_FILENAME);
    Path propsF = storageLocation.resolve(PROPS_FILENAME);
    String saltContent = io.loadFile(saltFile, Charset.defaultCharset());
    String ivContent = io.loadFile(ivFile,Charset.defaultCharset());
    Properties props = io.loadProperties(propsF);
    LOG.debug("Salt is {}, iv is {}, props is {}", new Object[]{saltContent, ivContent, props});
    if(saltContent!=null){
      salt = hex.decode(saltContent.toCharArray());
    }
    if(ivContent!=null){
      initVector = hex.decode(ivContent.toCharArray());
    }
    if(props!=null){
      minIteration = conv.toInt(props.getProperty("iterations"), minIteration);
    }
    if(salt==null||initVector==null){
      createData();
    }
  }

  @Override
  public byte[] getSalt() {
    return salt;
  }

  @Override
  public int getMinIteration() {
    return minIteration;
  }

  @Override
  public byte[] getInitVector() {
    return initVector;
  }

  @Override
  public void createData() {
    LOG.debug("Creating new salt, init vector etc.");
    salt=bytes.createRandomByteArray(512);
    initVector=bytes.createRandomByteArray(16);
    minIteration=65535;
  }

  @Override
  public void storeData(Charset cs) {
    Path saltFile =storageLocation.resolve(SALT_FILENAME) ;
    Path ivFile = storageLocation.resolve(IV_FILENAME);
    Path propsF = storageLocation.resolve(PROPS_FILENAME);
    LOG.info("Writing security data to {}",storageLocation);
    Set<PosixFilePermission> perms =
        PosixFilePermissions.fromString("rwxr-x---");
    FileAttribute<Set<PosixFilePermission>> attr =
        PosixFilePermissions.asFileAttribute(perms);
    try {
      Files.createDirectories(storageLocation,attr);
      io.writeFile(salt,saltFile,"rw-r-----");
      io.writeFile(initVector,ivFile,"rw-r-----");
    }catch (IOException e) {
      LOG.error("Error when writing the salt file!",e);
    }
  }
}
