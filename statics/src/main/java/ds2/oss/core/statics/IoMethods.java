package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by dstrauss on 19.05.16.
 */
public interface IoMethods {
    /**
     * A logger.
     */
    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Recursively deletes a given path directory.
     *
     * @param directory the directory to delete
     * @throws IOException if an IO error occurred
     */
    static void deleteTree(Path directory) throws IOException {
        if (Files.exists(directory)) {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    LOG.debug("Deleting {}", file);
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    LOG.debug("Deleting directory {}", dir);
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }

            });
        }
    }

    static void touchFile(Path file) throws IOException {
        Path dir = file.getParent();
        Files.createDirectories(dir);
        Files.write(file, "".getBytes("utf-8"), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
    }

    static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                LOG.debug("Error when closing the given input stream!", e);
            }
        }
    }
}
