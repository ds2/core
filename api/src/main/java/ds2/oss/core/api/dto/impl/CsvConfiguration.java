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