/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
