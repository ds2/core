/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.dbtools.modules;

import javax.persistence.Column;
import sun.text.normalizer.NormalizerBase;

/**
 *
 * @author deindesign
 */
public class NormalizedNameModule implements {
    @Column(name="norm_name",nullable = false)
    private String normalizedName;
}
