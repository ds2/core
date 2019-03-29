package ds2.oss.core.jee.jwt.impl;

import ds2.oss.core.jee.jwt.api.Algorithm;
import ds2.oss.core.jee.jwt.api.TokenSigner;

public abstract class AbstractSignerImpl implements TokenSigner {
    private final Algorithm algorithm;

    AbstractSignerImpl(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
