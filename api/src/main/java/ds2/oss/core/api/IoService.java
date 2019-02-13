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
package ds2.oss.core.api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Properties;

/**
 * The famous IO service to deal with IO operations.
 *
 * @author dstrauss
 * @version 0.1
 */
public interface IoService {
    /**
     * Creates some directories.
     *
     * @param storageLocation
     *            the directory to create
     * @param attr
     *            some directory attributes to use when creating
     */
    void createDirectories(Path storageLocation, FileAttribute<?> attr);

    /**
     * Loads a file.
     *
     * @param file
     *            the file to load
     * @param cs
     *            The charset
     * @return the content, or null if an error occurred
     */
    String loadFile(Path file, Charset cs);

    /**
     * Loads the properties from a given file.
     *
     * @param file
     *            the properties file
     * @return the loaded file, or null
     */
    Properties loadProperties(Path file);

    /**
     * Loads some properties from the given resource location.
     *
     * @param resLocation
     *            the resource location
     *
     * @return the loaded properties, or an empty properties object
     */
    Properties loadProperties(String resLocation);

    /**
     * Loads a resource.
     *
     * @param resName
     *            the resource name
     *
     * @return the resource content, or null if not found or an error occurred
     */
    String loadResource(String resName);

    /**
     * Writes some file data.
     *
     * @param data
     *            the byte data
     * @param target
     *            the target to write to
     * @param permissionMask
     *            a possible unix permission mask
     * @throws IOException
     *             if an IO error occurred
     */
    void writeFile(byte[] data, Path target, String permissionMask) throws IOException;

    /**
     * Writes some file data.
     *
     * @param data
     *            the string content
     * @param cs
     *            the target charset to use to convert the string into bytes
     * @param target
     *            the target to write to
     * @param permissionMask
     *            a possible unix permission mask
     * @throws IOException
     *             if an IO error occurred
     */
    void writeFile(String data, Charset cs, Path target, String permissionMask) throws IOException;

    /**
     * Writes a properties collection.
     *
     * @param props
     *            the properties
     *
     * @param target
     *            the target to write to
     * @param permissionMask
     *            a possible unix permission mask
     * @throws IOException
     *             if an IO error occurred
     */
    void writeProperties(Properties props, Path target, String permissionMask) throws IOException;
}
