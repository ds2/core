package ds2.oss.core.base.impl;

import ds2.oss.core.api.IoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.util.Properties;

/**
 * The IO service impl.
 *
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
public class IoServiceImpl implements IoService {
  private static final Logger LOG = LoggerFactory.getLogger(IoServiceImpl.class);

  @Override
  public Properties loadProperties(String resLocation) {
    Properties props = new Properties();
    try (InputStream is = getClass().getResourceAsStream(resLocation)) {
      props.load(is);
    } catch (IOException e) {
      LOG.debug("Error when loading the resource.", e);
    }
    return props;
  }

  @Override
  public String loadResource(String resName) {
    InputStream is = getClass().getResourceAsStream(resName);
    String rc = null;
    if (is != null) {
      Reader isr = new InputStreamReader(is);
      try (BufferedReader br = new BufferedReader(isr)) {
        StringBuilder sb = new StringBuilder();
        while (true) {
          String line = br.readLine();
          if (line == null) {
            break;
          }
          sb.append(line);
        }
        rc = sb.toString();
      } catch (IOException e) {
        LOG.debug("Error occurred on reading!", e);
      }
    }
    return rc;
  }
}
