package ds2.oss.core.elasticsearch.impl;

import ds2.oss.core.elasticsearch.api.CodecProvider;
import ds2.oss.core.elasticsearch.api.EsCodec;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
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
  private Instance<TypeCodec> foundCodecs;
  @Inject
  private Provider<TypeCodec> otherCodes;

  @Override
  public <T> TypeCodec<T> findFor(final Class<T> c) {
    if (c == null) {
      return null;
    }
    TypeCodec<T> rc = null;
    Annotation a = new EsCodecTyped<>(c);
    LOG.debug("Annotation of codec should be {}", a);
    try {
      rc = foundCodecs.select(a).get();
    } catch (RuntimeException e) {
      LOG.warn("Error when looking up an instance of a codec!", e);
    }
    return rc;
  }

  /**
   * The annotation literal for the ESCodecs having a specific value.
   *
   * @param <T> the type of the value
   */
  private class EsCodecTyped<T> extends AnnotationLiteral<EsCodec> implements EsCodec {
    private Class<T> c;

    public EsCodecTyped(Class<T> c) {
      super();
      this.c = c;
    }

    public Class<T> value() {
      return c;
    }
  }
}
