/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.dbtools.modules;

import ds2.oss.core.api.settable.NormalizedNameAware;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author deindesign
 */
@Embeddable
public class NormalizedNameModule implements NormalizedNameAware {

    private static final long serialVersionUID = -5009052399914767060L;

    @Column(name = "norm_name", nullable = false, unique = true)
    private String normalizedName;

    @Override
    public String getNormalizedName() {
        return normalizedName;
    }

    @Override
    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

}
