/*
 * Copyright 2012-2013 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.base.impl;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.IoService;

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
    String resName2 = resName;
    if (!resName.startsWith("/")) {
      resName2 = "/" + resName;
    }
    final InputStream is = getClass().getResourceAsStream(resName2);
    String rc = null;
    if (is != null) {
      final Reader isr = new InputStreamReader(is, Charset.forName("utf-8"));
      try (BufferedReader br = new BufferedReader(isr)) {
        final StringBuilder sb = new StringBuilder();
        while (true) {
          final String line = br.readLine();
          if (line == null) {
            break;
          }
          sb.append(line);
        }
        rc = sb.toString();
      } catch (final IOException e) {
        LOG.debug("Error occurred on reading!", e);
      }
    } else {
      LOG.warn("Could not find resource {}!", resName2);
    }
    return rc;
  }

  @Override
  public String loadFile(Path file, Charset cs) {
    String rc = null;
    if (Files.isReadable(file)) {
      try {
        byte[] bytes = Files.readAllBytes(file);
        rc = new String(bytes, cs);
      } catch (IOException e) {
        LOG.error("Error when reading the file from " + file, e);
      }
    }
    return rc;
  }

  @Override
  public Properties loadProperties(Path file) {
    Properties rc = new Properties();
    try (InputStream is = Files.newInputStream(file)) {
      rc.load(is);
    } catch (IOException e) {
      LOG.error("Error when reading the properties from file " + file, e);
    }
    return rc;
  }
}
