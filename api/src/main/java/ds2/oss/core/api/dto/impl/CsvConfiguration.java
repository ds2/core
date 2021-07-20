/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.api.dto.impl;

import lombok.Builder;
import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Builder
@Data
public class CsvConfiguration {
    @Builder.Default
    private char separator = ',';
    @Builder.Default
    private boolean ignoreQuotations = true;
    @Builder.Default
    private char quotation = '\"';
    @Builder.Default
    private int skipLines = 0;
    @Builder.Default
    private int skipColumns = 0;
    @Builder.Default
    private Charset encoding = StandardCharsets.UTF_8;
    @Builder.Default
    private String lineSeparator = "\n";
}
