/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.dbtools.modules;

import ds2.oss.core.api.settable.NormalizedNamedAware;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 *
 * @author deindesign
 */
@Embeddable
public class NamedNormalizedModule implements NormalizedNamedAware {

    private static final long serialVersionUID = -8048468907853527416L;
    @Embedded
    private NamedAwareModule nam = new NamedAwareModule();
    @Embedded
    private NormalizedNameModule nnm = new NormalizedNameModule();

    @Override
    public String getName() {
        return nam.getName();
    }

    @Override
    public String getNormalizedName() {
        return nnm.getNormalizedName();
    }

    @Override
    public void setName(String s) {
        nam.setName(s);
    }

    @Override
    public void setNormalizedName(String s) {
        nnm.setNormalizedName(s);
    }

}
