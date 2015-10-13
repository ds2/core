/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.dbtools.modules;

import ds2.oss.core.api.settable.NormalizedNamedDescriptiveAware;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author deindesign
 */
@Embeddable
public class NamedNormalizedDescriptiveModule implements NormalizedNamedDescriptiveAware {

    private static final long serialVersionUID = -884204081077605434L;

    private NamedNormalizedModule nnm = new NamedNormalizedModule();
    @Column(name = "description")
    private String description;

    @Override
    public String getName() {
        return nnm.getName();
    }

    @Override
    public String getNormalizedName() {
        return nnm.getNormalizedName();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setName(String s) {
        nnm.setName(s);
    }

    @Override
    public void setNormalizedName(String s) {
        nnm.setNormalizedName(s);
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
