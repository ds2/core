/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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

import ds2.oss.core.api.NameNormalizer;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author dstrauss
 */
@ApplicationScoped
public class NameNormalizerImpl implements NameNormalizer {

    @Override
    public String normalize(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        s = s.replaceAll("[\u00ae\u201c\\)\\(\\]\\[\'\"]", "");
        s = s.replaceAll("[\\s\u00f0\\/\\&:\\,\\.\\|\\\u2013\\-]", "-");
        s = s.replaceAll("[\u00fc]", "ue");
        s = s.replaceAll("[\u00e4\u00e6]", "ae");
        s = s.replaceAll("[\u00f6\u00f8]", "oe");
        s = s.replaceAll("[\u00df]", "ss");
        s = s.replaceAll("[\u00e7]", "ch");
        s = s.replaceAll("[\u00e5\u00f4]", "o");
        s = s.replaceAll("[\u00ee\u00ed\u00ec]", "i");
        s = s.replaceAll("[\u00ea\u00e8\u00e9\u00eb]", "e");
        s = s.replaceAll("[\u00e2\u00e1\u00e0]", "a");
        s = s.replaceAll("[+]", "plus");
        s = s.replaceAll("\u00f1", "n");
        s = s.replaceAll("\u20ac", "eur");
        StringBuilder sb = new StringBuilder(s);
        return sb.toString();
    }

}
