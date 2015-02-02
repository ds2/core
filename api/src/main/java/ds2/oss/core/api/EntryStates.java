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
package ds2.oss.core.api;

import java.util.HashMap;
import java.util.Map;

/**
 * The states of persistable entries.
 * 
 * @author dstrauss
 * @version 0.1
 */
public enum EntryStates implements NumericEnumValue {
    /**
     * The entry is new, in draft.
     */
    PREPARED(0),
    /**
     * The entry is active.
     */
    ACTIVE(1),
    /**
     * The entry is deleted.
     */
    DELETED(3),
    /**
     * The entry is locked and must not be modified.
     */
    LOCKED(2);
    
    /**
     * A cache map.
     */
    private static final Map<Integer, EntryStates> cacheMap = new HashMap<>(4);
    static {
        cacheMap.put(PREPARED.getNumericalValue(), PREPARED);
        cacheMap.put(ACTIVE.getNumericalValue(), ACTIVE);
        cacheMap.put(LOCKED.getNumericalValue(), LOCKED);
        cacheMap.put(DELETED.getNumericalValue(), DELETED);
    }
    /**
     * The numerical value.
     */
    private final int numericalValue;
    
    /**
     * Inits the enum value.
     * 
     * @param i
     *            the numerical value
     */
    private EntryStates(final int i) {
        numericalValue = i;
    }
    
    @Override
    public int getNumericalValue() {
        return numericalValue;
    }
    
    /**
     * Returns the entry state for the given id.
     * 
     * @param i
     *            the id of the enum value
     * @return the enum value, or null if not found
     */
    public static EntryStates getById(final int i) {
        return cacheMap.get(i);
    }
}
