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
package ds2.oss.core.dbtools.support;

import ds2.oss.core.api.DatabaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Persistence;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Created by dstrauss on 15.06.16.
 */
public class CreateSchemaHelper {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * To create sql schema files based on the persistence unit and the target database type.
     *
     * @param persistenceUnitName the persistence unit name
     * @param type                the database type
     */
    public void createSqlSchemaFiles(String persistenceUnitName, DatabaseType type, Path baseDir) {
        LOG.info("Using persistente unit {} as base to generate the schema files..", persistenceUnitName);
        final Properties persistenceProperties = new Properties();
        switch (type) {
            case Postgresql:
                persistenceProperties.put("javax.persistence.database-product-name", "Postgresql");
                persistenceProperties.put("javax.persistence.database-major-version", "9");
                persistenceProperties.put("javax.persistence.database-minor-version", "6");
                break;
            default:
                throw new IllegalStateException("Currently not suported: " + type);
        }
        persistenceProperties.put("javax.persistence.schema-generation.scripts.action", "drop-and-create");
        String filenameBase = persistenceUnitName + "_" + type.name().toLowerCase();
        String dropSql = filenameBase + "_dropStatements.sql";
        String createSql = filenameBase + "_createStatements.sql";
        if (!Files.exists(baseDir)) {
            try {
                Files.createDirectories(baseDir);
            } catch (IOException e) {
                LOG.error("Error when creating the target directory to write to!", e);
            }
        }
        Path dropPath = baseDir.resolve(dropSql);
        Path createPath = baseDir.resolve(createSql);
        LOG.info("Using paths {} and {}", dropPath, createPath);
        persistenceProperties.put("javax.persistence.schema-generation.scripts.drop-target", dropPath.toString());
        persistenceProperties.put("javax.persistence.schema-generation.scripts.create-target", createPath.toAbsolutePath());
        LOG.debug("Starting generator..");
        Persistence.generateSchema(persistenceUnitName, persistenceProperties);
        LOG.info("Hopefully done now; files should be in {}, {} and {}", new Object[]{baseDir.toAbsolutePath(), dropPath, createPath});
    }
}
