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

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.ConverterTool;
import ds2.oss.core.api.HexCodec;
import ds2.oss.core.api.IoService;
import ds2.oss.core.api.PathLocation;
import ds2.oss.core.api.SecurityBaseDataService;
import ds2.oss.core.api.crypto.BytesProvider;

/**
 * The impl.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class SecurityBaseDataServiceImpl implements SecurityBaseDataService {
    /**
     * The system property name.
     */
    public static final String SYS_PROPERTY = "ds2.app.sec.home";
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The filename of the salt file.
     */
    private static final String SALT_FILENAME = "0xsalt.txt";
    /**
     * The filename of the init vector file.
     */
    private static final String IV_FILENAME = "0xiv.txt";
    /**
     * The properties filename.
     */
    private static final String PROPS_FILENAME = "sec.properties";
    
    /**
     * The hex codec.
     */
    @Inject
    private HexCodec hex;
    /**
     * The IO service.
     */
    @Inject
    private IoService io;
    /**
     * The converter.
     */
    @Inject
    private ConverterTool conv;
    /**
     * The salt.
     */
    private byte[] salt;
    /**
     * The init vector.
     */
    private byte[] initVector;
    /**
     * The iteration count.
     */
    private int minIteration = 65535;
    /**
     * The bytes provider.
     */
    @Inject
    private BytesProvider bytes;
    /**
     * The storage location to load or write data to.
     */
    @Inject
    @PathLocation(property = SYS_PROPERTY, environment = "DS2_APPSEC_HOME")
    private Path storageLocation;
    
    /**
     * Actions to perform on load.
     */
    @PostConstruct
    public void onLoad() {
        if (storageLocation == null) {
            storageLocation = Paths.get(System.getProperty("user.home"), ".ds2AppSec");
        }
        LOG.debug("Location to use is {}", storageLocation);
        final Path saltFile = storageLocation.resolve(SALT_FILENAME);
        final Path ivFile = storageLocation.resolve(IV_FILENAME);
        final Path propsF = storageLocation.resolve(PROPS_FILENAME);
        final String saltContent = io.loadFile(saltFile, Charset.defaultCharset());
        final String ivContent = io.loadFile(ivFile, Charset.defaultCharset());
        final Properties props = io.loadProperties(propsF);
        LOG.debug("Salt is {}, iv is {}, props is {}", new Object[] { saltContent, ivContent, props });
        if (saltContent != null) {
            salt = hex.decode(saltContent.toCharArray());
        }
        if (ivContent != null) {
            initVector = hex.decode(ivContent.toCharArray());
        }
        if (props != null) {
            minIteration = conv.toInt(props.getProperty("iterations"), minIteration);
        }
        if ((salt == null) || (initVector == null)) {
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
        salt = bytes.createRandomByteArray(512);
        initVector = bytes.createRandomByteArray(16);
        minIteration = 65535;
    }
    
    @Override
    public void storeData(final Charset cs) {
        final Path saltFile = storageLocation.resolve(SALT_FILENAME);
        final Path ivFile = storageLocation.resolve(IV_FILENAME);
        final Path propsF = storageLocation.resolve(PROPS_FILENAME);
        LOG.info("Writing security data to {}", storageLocation);
        final Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
        final FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        try {
            io.createDirectories(storageLocation, attr);
            io.writeFile(salt, saltFile, "rw-r-----");
            io.writeFile(initVector, ivFile, "rw-r-----");
            final Properties props = new Properties();
            props.setProperty("iterations", "" + minIteration);
            io.writeProperties(props, propsF, "rwxr-x---");
        } catch (final IOException e) {
            LOG.error("Error when writing the salt file!", e);
        }
    }
}
