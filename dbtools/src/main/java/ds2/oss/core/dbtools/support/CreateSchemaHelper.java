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
                persistenceProperties.put("javax.persistence.database-minor-version", "4");
                break;
            default:
                throw new IllegalStateException("Currently not suported: " + type);
        }
        persistenceProperties.put("javax.persistence.schema-generation.scripts.action", "drop-and-create");
        String filenameBase = "jpa21_schemagen_" + type.name().toLowerCase();
        String dropSql = filenameBase + "_dropStatements.sql";
        String createSql = filenameBase + "_createStatements.sql";
        if(!Files.exists(baseDir)){
            try {
                Files.createDirectories(baseDir);
            } catch (IOException e) {
                LOG.error("Error when creating the target directory to write to!",e);
            }
        }
        Path dropPath = baseDir.resolve(dropSql);
        Path createPath = baseDir.resolve(createSql);
        LOG.info("Using paths {} and {}", dropPath, createPath);
        persistenceProperties.put("javax.persistence.schema-generation.scripts.drop-target", dropPath.toString());
        persistenceProperties.put("javax.persistence.schema-generation.scripts.create-target", createPath.toAbsolutePath());
        LOG.debug("Starting generator..");
        Persistence.generateSchema(persistenceUnitName, persistenceProperties);
        LOG.info("Hopefully done now; files should be in {}", baseDir);
    }
}
