package ds2.oss.core.elasticsearch.impl;

import ds2.oss.core.elasticsearch.api.EsCodec;

import javax.enterprise.util.AnnotationLiteral;

/**
 * Created by dstrauss on 22.07.13.
 */
public class EsCodecAnnotationLiteral extends AnnotationLiteral<EsCodec> implements EsCodec {
  private Class<?> c;

  public EsCodecAnnotationLiteral(Class<?> c) {
    this.c = c;
  }

  @Override
  public Class<?> value() {
    return c;
  }
}
