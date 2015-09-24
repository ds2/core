/**
 * 
 */
package ds2.oss.core.base.impl;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.NameNormalizer;

/**
 * @author dstrauss
 *         
 */
@ApplicationScoped
public class NameNormalizerImpl implements NameNormalizer {
    
    @Override
    public String normalize(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        s = s.replaceAll("[®“\\)\\(\\]\\[\'\"]", "");
        s = s.replaceAll("[\\sð\\/\\&:\\,\\.\\|\\–\\-]", "-");
        s = s.replaceAll("[ü]", "ue");
        s = s.replaceAll("[äæ]", "ae");
        s = s.replaceAll("[öø]", "oe");
        s = s.replaceAll("[ß]", "ss");
        s = s.replaceAll("[ç]", "ch");
        s = s.replaceAll("[åô]", "o");
        s = s.replaceAll("[îíì]", "i");
        s = s.replaceAll("[êèéë]", "e");
        s = s.replaceAll("[âáà]", "a");
        s = s.replaceAll("[+]", "plus");
        s = s.replaceAll("ñ", "n");
        s = s.replaceAll("€", "eur");
        StringBuilder sb = new StringBuilder(s);
        return sb.toString();
    }
    
}
