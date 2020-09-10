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
package ds2.oss.core.csv;

import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.CsvParser;
import ds2.oss.core.api.dto.impl.CsvConfiguration;
import ds2.oss.core.statics.IoMethods;

import javax.enterprise.context.Dependent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Dependent
public class CsvParserImpl implements CsvParser {

    @Override
    public List<List<String>> readFromClasspath(CsvConfiguration configuration, String pathToCsvFromClasspath) throws CoreException {
        InputStream thisInputStream = getClass().getResourceAsStream(pathToCsvFromClasspath);
        if (thisInputStream == null) {
            throw new CoreException(CoreErrors.IllegalArgument, "The following path was not found: " + pathToCsvFromClasspath);
        }
        InputStreamReader inputStreamReader = null;
        Scanner lineScanner=null;
        List<List<String>> csvData=new ArrayList<>(10);
        try {
            inputStreamReader = new InputStreamReader(thisInputStream, configuration.getEncoding());
            lineScanner=new Scanner(inputStreamReader);
            lineScanner.useDelimiter(configuration.getLineSeparator());
        } finally {
            IoMethods.close(thisInputStream);
            IoMethods.close(inputStreamReader);
            IoMethods.close(lineScanner);
        }
        return csvData;
    }
}
