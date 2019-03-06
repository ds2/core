package ds2.oss.core.api.dto.impl;

import lombok.Builder;
import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Builder
@Data
public class CsvConfiguration {
    private char separator = ',';
    private boolean ignoreQuotations = true;
    private char quotation = '\"';
    private int skipLines = 0;
    private int skipColumns = 0;
    private Charset encoding = StandardCharsets.UTF_8;
    private String lineSeparator = "\n";
}
