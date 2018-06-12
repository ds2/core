package ds2.oss.core.api.crypto;

/**
 * Created by deindesign on 24.03.16.
 */
public enum SunEllipticCurveNames implements AlgorithmNamed {
    Curve1174("curve1174"),;
    private String name;
    private SunEllipticCurveNames(String s){
        name=s;
    }

    @Override
    public String getAlgorithmName() {
        return name;
    }
}
