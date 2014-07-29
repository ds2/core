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
