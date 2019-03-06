package ds2.oss.core.api;

import ds2.oss.core.api.dto.impl.CsvConfiguration;

import java.util.List;

public interface CsvParser {
    List<List<String>> readFromClasspath(CsvConfiguration configuration, String pathToCsvFromClasspath) throws CoreException;
}
