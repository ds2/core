/*
 * Copyright 2012-2015 Dirk Strauss
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
 * @param <K>
 *            the map key type
 * @param <V>
 *            the map value type
 */
public final class MapBuilder<K, V> {
    /**
     * The backing map.
     */
    private Map<K, V> map;
    
    /**
     * Inits the hash map with the given size.
     * 
     * @param initSize
     *            the size. If less than 0, initSize will be ignored
     */
    private MapBuilder(final int initSize) {
        if (initSize > 0) {
            map = new HashMap<>(initSize);
        } else {
            map = new HashMap<>();
        }
        
    }
    
    /**
     * Creates the map builder with the given key class and value class.
     * 
     * @param kClass
     *            the key class
     * @param vClass
     *            the value class
     * @return the map builder
     */
    public static <K, V> MapBuilder<K, V> createWith(final Class<K> kClass, final Class<V> vClass) {
        return createWith(kClass, vClass, 0);
    }
    
    /**
     * Creates the map builder with the given key class, value class and init size.
     * 
     * @param kClass
     *            the key class
     * @param vClass
     *            the value class
     * @param initSize
     *            the init size
     * @return the map builder
     */
    public static <K, V> MapBuilder<K, V> createWith(final Class<K> kClass, final Class<V> vClass, final int initSize) {
        return new MapBuilder<>(initSize);
    }
    
    /**
     * Puts an element into the map.
     * 
     * @param k
     *            the key
     * @param v
     *            the value
     * @return this map builder
     */
    public MapBuilder<K, V> put(final K k, final V v) {
        map.put(k, v);
        return this;
    }
    
    /**
     * Returns the backing map.
     * 
     * @return the backing map
     */
    public Map<K, V> build() {
        return map;
    }
    
}
