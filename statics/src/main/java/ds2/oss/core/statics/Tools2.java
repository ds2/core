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
package ds2.oss.core.statics;

import java.util.Collection;

/**
 * Created by deindesign on 21.12.15.
 */
public interface Tools2 {
    /**
     * Returns the first element from a given collection, or the default value.
     * @param c the collection
     * @param def the default value
     * @param <E> the type
     * @param <V> the value type
     * @return the first element of the collection, or null
     */
    static <E,V> E getNullOrElementFromCollection(Collection<E> c, E def){
        E rc=def;
        if(c!=null){
            rc=c.iterator().next();
        }
        return rc;
    }

    /**
     * Returns the first non-null element from the given sequence of items.
     * @param e the items
     * @param <E> the type
     * @return the first non-null element, or null
     */
    static <E> E getFirstNonNullElement(E...e){
        E rc=null;
        for(E eItem : e){
            if(eItem!=null){
                rc=eItem;
                break;
            }
        }
        return rc;
    }
}
