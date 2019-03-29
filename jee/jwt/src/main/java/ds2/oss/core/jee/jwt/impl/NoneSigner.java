package ds2.oss.core.jee.jwt.impl;

import ds2.oss.core.jee.jwt.api.Algorithm;
import ds2.oss.core.jee.jwt.api.JwtContentException;
import ds2.oss.core.jee.jwt.api.JwtKeyException;

public class NoneSigner extends AbstractSignerImpl {

    public NoneSigner() {
        super(Algorithm.NONE);
    }

    @Override
    public String getBase64UrlSignatureFrom(String headerDotBodyBase64UrlString) throws JwtContentException, JwtKeyException {
        return null;
    }
}
