/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.AppServerSecurityBaseDataService;
import ds2.oss.core.api.ConverterTool;
import ds2.oss.core.api.HexCodec;
import ds2.oss.core.api.IoService;
import ds2.oss.core.api.annotations.PathLocation;
import ds2.oss.core.api.crypto.BytesProvider;
import ds2.oss.core.api.crypto.KeyGeneratorService;
import ds2.oss.core.base.impl.AlternateSecurityBaseDataImpl;

/**
 * The impl for application servers.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Specializes
public class AppServerSecurityBaseDataServiceImpl extends AlternateSecurityBaseDataImpl
    implements
    AppServerSecurityBaseDataService {

    /**
     * A simple lock.
     */
    private static final Lock LOCK = new ReentrantLock();
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The properties filename.
     */
    private static final String PROPS_FILENAME = "sec.properties";
    /**
     * The filename containing the AES secret key data.
     */
    private static final String SK_FILENAME = "0xsk.txt";

    /**
     * The AES secret key.
     */
    private SecretKey aesSecretKey;
    /**
     * The bytes provider.
     */
    @Inject
    private BytesProvider bytes;
    /**
     * The converter.
     */
    @Inject
    private ConverterTool conv;
    /**
     * The hex codec.
     */
    @Inject
    private HexCodec hex;
    /**
     * The init vector.
     */
    private byte[] initVector;
    /**
     * The IO service.
     */
    @Inject
    private IoService io;
    /**
     * The key generator service.
     */
    @Inject
    private KeyGeneratorService kg;
    /**
     * The iteration count.
     */
    private int minIteration = 65535;
    /**
     * The salt.
     */
    private byte[] salt;
    /**
     * The storage location to load or write data to.
     */
    @Inject
    @PathLocation(property = SYS_PROPERTY, environment = "DS2_APPSEC_HOME")
    private Path storageLocation;

    @Override
    public void createData() {
        try {
            LOCK.lock();
            LOG.debug("Creating new salt, init vector etc.");
            salt = bytes.createRandomByteArray(512);
            initVector = bytes.createRandomByteArray(16);
        } finally {
            LOCK.unlock();
        }
        aesSecretKey = kg.generateAesKey();
    }

    @Override
    public SecretKey getAppserverSecretKey() {
        return aesSecretKey;
    }

    @Override
    public byte[] getInitVector() {
        try {
            LOCK.lock();
            return initVector = bytes.createRandomByteArray(16);
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public int getMinIteration() {
        return minIteration;
    }

    @Override
    public byte[] getSalt() {
        try {
            LOCK.lock();
            return salt;
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Store the data on exit.
     */
    @PreDestroy
    public void onExit() {
        storeData(Charset.defaultCharset());
    }

    /**
     * Actions to perform on load.
     */
    @PostConstruct
    public void onLoad() {
        if (storageLocation == null) {
            storageLocation = Paths.get(System.getProperty("user.home"), ".ds2AppSec");
        }
        LOG.debug("Location to use is {}", storageLocation);
        final String skContent = io.loadFile(storageLocation.resolve(SK_FILENAME), Charset.defaultCharset());
        final Properties props = io.loadProperties(storageLocation.resolve(PROPS_FILENAME));
        LOG.debug("Props is {}", new Object[] { props });
        if (props != null) {
            minIteration = conv.toInt(props.getProperty("iterations"), minIteration);
            if (minIteration <= 1000) {
                LOG.warn("MinIt too small, using base default value!");
                minIteration = 65535;
            }
        }
        if (salt == null || initVector == null) {
            createData();
        }
        if (skContent != null) {
            aesSecretKey = kg.generateAesFromBytes(hex.decode(skContent.toCharArray()));
        }
    }

    @Override
    public void storeData(final Charset cs) {
        final Path propsF = storageLocation.resolve(PROPS_FILENAME);
        final Path aesF = storageLocation.resolve(SK_FILENAME);
        LOG.info("Writing security data to {}", storageLocation);
        final Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
        final FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        try {
            LOCK.lock();
            io.createDirectories(storageLocation, attr);
            io.writeFile(hex.encode(aesSecretKey.getEncoded()), Charset.defaultCharset(), aesF, "rw-------");
            final Properties props = new Properties();
            props.setProperty("iterations", "" + minIteration);
            io.writeProperties(props, propsF, "rwxr-x---");
        } catch (final IOException e) {
            LOG.error("Error when writing the salt file!", e);
        } finally {
            LOCK.unlock();
        }
    }
}
