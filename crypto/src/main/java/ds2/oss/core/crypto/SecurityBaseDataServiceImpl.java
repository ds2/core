package ds2.oss.core.crypto;

import ds2.oss.core.api.ConverterTool;
import ds2.oss.core.api.HexCodec;
import ds2.oss.core.api.IoService;
import ds2.oss.core.api.SecurityBaseDataService;
import ds2.oss.core.api.crypto.BytesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.File;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * The impl.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class SecurityBaseDataServiceImpl implements SecurityBaseDataService {
  private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  @Inject
  private HexCodec hex;
  @Inject
  private IoService io;
  @Inject
  private ConverterTool conv;
  private byte[] salt;
  private byte[] initVector;
  private int minIteration = 65535;
  @Inject
  private BytesProvider bytes;

  @PostConstruct
  public void onLoad() {
    Path defaultLocation= Paths.get(System.getProperty("user.home"),".ds2AppSec");
    Path envHomeDir=Paths.get(System.getProperty("ds2.app.sec.home"));
    Path location=defaultLocation;
    if (Files.exists(envHomeDir)) {
      location = envHomeDir;
    }
    LOG.debug("Location to use is {}", location);
    Path saltFile =location.resolve("0xsalt.txt") ;
    Path ivFile = location.resolve("0xiv.txt");
    Path propsF = location.resolve("sec.properties");
    String saltContent = io.loadFile(saltFile, Charset.defaultCharset());
    String ivContent = io.loadFile(ivFile,Charset.defaultCharset());
    Properties props = io.loadProperties(propsF);
    LOG.debug("Salt is {}, iv is {}, props is {}", new Object[]{saltContent, ivContent, props});
    salt = hex.decode(saltContent.toCharArray());
    initVector = hex.decode(ivContent.toCharArray());
    minIteration = conv.toInt(props.getProperty("iterations"), minIteration);
  }

  @Override
  public byte[] getSalt() {
    return salt;
  }

  @Override
  public int getMinIteration() {
    return minIteration;
  }

  @Override
  public byte[] getInitVector() {
    return initVector;
  }

  @Override
  public void createData() {
    salt=bytes.createRandomByteArray(512);
    initVector=bytes.createRandomByteArray(16);
    minIteration=65535;
  }
}
