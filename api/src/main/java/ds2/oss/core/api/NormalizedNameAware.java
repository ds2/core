/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api;

import java.io.Serializable;

/**
 *
 * @author deindesign
 */
public interface NormalizedNameAware extends Serializable {

    /**
     * Returns the normalized name of this object.
     *
     * @return the normalized name
     */
    String getNormalizedName();
}
