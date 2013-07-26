package ds2.oss.core.base.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
            final Reader isr = new InputStreamReader(is);
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
        }
        return rc;
    }
}
