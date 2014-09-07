package ds2.oss.core.api.environment;

/**
 * The most familiar cluster names.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
public enum Clusters implements Cluster {
    /**
     * Cluster A.
     */
    A,
    /**
     * Cluster B.
     */
    B,
    /**
     * Cluster C.
     */
    C,
    /**
     * Cluster D.
     */
    D,
    /**
     * Cluster E.
     */
    E,
    /**
     * Cluster F.
     */
    F,
    /**
     * Cluster G.
     */
    G,
    /**
     * Cluster H.
     */
    H,
    /**
     * Cluster I.
     */
    I,
    /**
     * Cluster J.
     */
    J,
    /**
     * Cluster K.
     */
    K,
    /**
     * Cluster L.
     */
    L,
    /**
     * Cluster M.
     */
    M,
    /**
     * Cluster N.
     */
    N,
    /**
     * Cluster O.
     */
    O,
    /**
     * Cluster P.
     */
    P,
    /**
     * Cluster Q.
     */
    Q,
    /**
     * Cluster R.
     */
    R,
    /**
     * Cluster S.
     */
    S,
    /**
     * Cluster T.
     */
    T,
    /**
     * Cluster U.
     */
    U,
    /**
     * Cluster V.
     */
    V,
    /**
     * Cluster W.
     */
    W,
    /**
     * Cluster X.
     */
    X,
    /**
     * Cluster Y.
     */
    Y,
    /**
     * Cluster Z.
     */
    Z;
    
    @Override
    public char getClusterName() {
        return toString().charAt(0);
    }
    
}
