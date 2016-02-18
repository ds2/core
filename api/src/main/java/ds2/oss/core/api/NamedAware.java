/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api;

import java.io.Serializable;

/**
 * Indicates that this object supports a name field.
 *
 * @author deindesign
 */
public interface NamedAware extends Serializable {

    /**
     * Returns the name of this object.
     *
     * @return the name
     */
    String getName();
}
