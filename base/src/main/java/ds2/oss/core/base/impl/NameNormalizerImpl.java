/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
