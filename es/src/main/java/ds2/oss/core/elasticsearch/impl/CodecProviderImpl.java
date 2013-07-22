package ds2.oss.core.elasticsearch.impl;

import ds2.oss.core.elasticsearch.api.CodecProvider;
import ds2.oss.core.elasticsearch.api.EsCodec;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Provider;
import java.lang.annotation.Annotation;

/**
 * A provider for any found ES codec.
 */
@ApplicationScoped
public class CodecProviderImpl implements CodecProvider {
  private final static Logger LOG = LoggerFactory.getLogger(CodecProviderImpl.class);
  @Inject
  @Any
  private Instance<TypeCodec> foundCodecs;

  @PostConstruct
  public void onClass() {
    if (foundCodecs.isUnsatisfied()) {
      LOG.warn("found codecs are unsatisfied!");
    }
  }

  @Override
  public <T> TypeCodec<T> findFor(final Class<T> c) {
    if (c == null) {
      return null;
    }
    TypeCodec<T> rc = null;
    Annotation a = new EsCodecAnnotationLiteral(c);
    LOG.debug("Annotation of codec should be {}", a);
    try {
      rc = foundCodecs.select(a).get();
    } catch (RuntimeException e) {
      LOG.warn("Error when looking up an instance of a codec!", e);
    }
    if (rc == null) {
      for (TypeCodec codec : foundCodecs) {
        LOG.debug("Having codec {}", codec);
      }
    }
    LOG.debug("rc will be {}", rc);
    return rc;
  }

  @Override
  public int getInstanceCount() {
    return 1;
  }

}
