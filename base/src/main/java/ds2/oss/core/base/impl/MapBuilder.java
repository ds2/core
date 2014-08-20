/*
 * Copyright 2012-2014 Dirk Strauss
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.base.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * A dummy map builder.
 *
 * @author dstrauss
 * @version 0.3
 */
public class MapBuilder<K, V> {

    private Map<K, V> map;

    private MapBuilder(int initSize) {
        if (initSize > 0) {
            map = new HashMap<>(initSize);
        } else {
            map = new HashMap<>();
        }

    }

    public static <K, V> MapBuilder<K, V> createWith(Class<K> kClass, Class<V> vClass) {
        return createWith(kClass, vClass, 0);
    }

    public static <K, V> MapBuilder<K, V> createWith(Class<K> kClass, Class<V> vClass, int initSize) {
        return new MapBuilder<>(initSize);
    }

    public MapBuilder<K, V> put(K k, V v) {
        map.put(k, v);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }

}
