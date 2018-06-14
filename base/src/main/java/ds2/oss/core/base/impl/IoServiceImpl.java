/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.base.impl;

import ds2.oss.core.api.IoService;
import ds2.oss.core.statics.IoMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Properties;
import java.util.Set;

/**
 * The IO service impl.
 *
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
public class IoServiceImpl implements IoService {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(IoServiceImpl.class);
    /**
     * The UTF8 charset.
     */
    private static final Charset UTF8 = Charset.forName("utf-8");
    /**
     * Frequent value to check if the current impl runs on windows.
     */
    private Boolean isWindows;

    @Override
    public void createDirectories(final Path storageLocation, final FileAttribute<?> attr) {
        if (Files.exists(storageLocation)) {
            LOG.debug("Path {} exists already!", storageLocation);
            return;
        }
        try {
            if (FileSystems.getDefault().isReadOnly()) {
                LOG.warn("Given filesystem is read only!");
                return;
            }
            if (attr == null || isWindows()) {
                Files.createDirectories(storageLocation);
            } else {
                Files.createDirectories(storageLocation, attr);
            }
        } catch (final IOException e1) {
            LOG.warn("Error when creating the directories!", e1);
        }
    }

    /**
     * Checks if the current OS is a windows OS.
     *
     * @return TRUE if OS name starts with Windows, otherwise FALSE
     */
    private boolean isWindows() {
        if (isWindows != null) {
            return isWindows.booleanValue();
        }
        final String osName = System.getProperty("os.name");
        LOG.debug("OS name is {}", osName);
        isWindows = Boolean.valueOf(osName.startsWith("Windows"));
        return isWindows.booleanValue();
    }

    @Override
    public String loadFile(final Path file, final Charset cs) {
        String rc = null;
        if (Files.isReadable(file)) {
            try {
                final byte[] bytes = Files.readAllBytes(file);
                rc = new String(bytes, cs);
            } catch (final IOException e) {
                LOG.error("Error when reading the file from " + file, e);
            }
        }
        return rc;
    }

    @Override
    public Properties loadProperties(final Path file) {
        Properties rc = new Properties();
        try (InputStream is = Files.newInputStream(file)) {
            rc.load(is);
        } catch (final IOException e) {
            LOG.debug("Error when reading the properties from file " + file, e);
            rc = null;
        }
        return rc;
    }

    @Override
    public Properties loadProperties(final String resLocation) {
        final Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream(resLocation)) {
            props.load(is);
        } catch (final IOException e) {
            LOG.debug("Error when loading the resource.", e);
        }
        return props;
    }

    @Override
    public String loadResource(final String resName) {
        return IoMethods.readResourceFromClasspath(resName, StandardCharsets.UTF_8);
    }

    @Override
    public void writeFile(final byte[] data, final Path target, final String permissionMask) throws IOException {
        try (OutputStream os = Files.newOutputStream(target, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            os.write(data);
        }
        if (permissionMask != null && !isWindows()) {
            final Set<PosixFilePermission> perms = PosixFilePermissions.fromString(permissionMask);
            Files.setPosixFilePermissions(target, perms);
        }
    }

    @Override
    public void writeFile(final String data, final Charset cs, final Path target, final String permissionMask)
            throws IOException {
        try (BufferedWriter os =
                     Files.newBufferedWriter(target, cs, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            os.write(data);
        }
        if (permissionMask != null && !isWindows()) {
            final Set<PosixFilePermission> perms = PosixFilePermissions.fromString(permissionMask);
            Files.setPosixFilePermissions(target, perms);
        }
    }

    @Override
    public void writeProperties(final Properties props, final Path target, final String permissionMask)
            throws IOException {
        try (final BufferedWriter bw =
                     Files.newBufferedWriter(target, UTF8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            props.store(bw, "Written now!");
        }
        if (permissionMask != null && !isWindows()) {
            final Set<PosixFilePermission> perms = PosixFilePermissions.fromString(permissionMask);
            Files.setPosixFilePermissions(target, perms);
        }
    }
}
