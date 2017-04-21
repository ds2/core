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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by deindesign on 21.12.15.
 */
public interface Methods {
    /**
     * A logger.
     */
    Logger LOG = LoggerFactory.getLogger(Methods.class);

    static Number parseNumber(String s) {
        Number rc = null;
        if (!isBlank(s)) {
            try {
                Double d = Double.valueOf(s);
                if (d.longValue() == d) {
                    rc = Long.valueOf(s);
                } else if (d.intValue() == d) {
                    rc = Integer.valueOf(s);
                } else {
                    rc = d;
                }
            } catch (NumberFormatException e) {
                //silently ignored
            }
        }
        return rc;
    }

    /**
     * Returns the first element from a given collection, or the default value.
     *
     * @param c   the collection
     * @param def the default value
     * @param <E> the type
     * @param <V> the value type
     * @return the first element of the collection, or null
     */
    static <E, V> E getNullOrElementFromCollection(Collection<E> c, E def) {
        E rc = def;
        if (c != null) {
            rc = c.iterator().next();
        }
        return rc;
    }

    /**
     * Returns the first non-null element from the given sequence of items.
     *
     * @param e   the items
     * @param <E> the type
     * @return the first non-null element, or null
     */
    static <E> E getFirstNonNullElement(E... e) {
        E rc = null;
        for (E eItem : e) {
            if (eItem != null) {
                rc = eItem;
                break;
            }
        }
        return rc;
    }

    static <E extends Enum<E>> E findEnumValueByName(Class<E> enumClass, String name, E defValue) {
        E rc = defValue;
        try {
            rc = Enum.valueOf(enumClass, name);
        } catch (IllegalArgumentException | NullPointerException e) {
            //ok, maybe lowercase/uppercase problem
            for (E e1 : enumClass.getEnumConstants()) {
                if (e1.name().equalsIgnoreCase(name)) {
                    rc = e1;
                    break;
                }
            }
        }
        return rc;
    }

    /**
     * Finds a given enum value inside an enum.
     *
     * @param enumClass the enum clas to check
     * @param name      the enum value name to look for
     * @param <E>       the enum type
     * @return the found enum value, or null if not found
     */
    static <E extends Enum<E>> E findEnumValueByName(Class<E> enumClass, String name) {
        LOG.debug("Checking for enum value {} in {}", name, enumClass);
        E rc = null;
        try {
            rc = Enum.valueOf(enumClass, name);
        } catch (IllegalArgumentException | NullPointerException e) {
            //ok, maybe lowercase/uppercase problem
            for (E e1 : enumClass.getEnumConstants()) {
                if (e1.name().equalsIgnoreCase(name)) {
                    rc = e1;
                    break;
                }
            }
        }
        LOG.debug("Returning possible result: {}", rc);
        return rc;
    }

    /**
     * Checks if the given string contains some value.
     *
     * @param s the string to check
     * @return TRUE if not blank, otherwise FALSE.
     * @deprecated Better use {@link #isBlank(String)} instead.
     */
    @Deprecated
    static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * Checks if the given string contains some value.
     *
     * @param s the string to check
     * @return TRUE if blank, otherwise FALSE.
     */
    static boolean isBlank(String s) {
        boolean rc = true;
        if (s != null && s.trim().length() > 0) {
            for (char c : s.toCharArray()) {
                if (!Character.isWhitespace(c)) {
                    rc = false;
                    break;
                }
            }
        }
        return rc;

    }

    /**
     * Compares two objects.
     *
     * @param s1 the first object
     * @param s2 the 2nd object
     * @return the result of the comparison
     */
    static <E extends Comparable<E>> int compare(E s1, E s2) {
        if (s1 != null) {
            return s1.compareTo(s2);
        } else if (s2 != null) {
            return -1 * s2.compareTo(s1);
        }
        // both are null
        return 0;
    }

    /**
     * Compares two strings.
     *
     * @param s1 the first string
     * @param s2 the 2nd string
     * @return the result of the comparison
     */
    static int compare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }
        if (s1 != null && s2 == null) {
            return -1;
        }
        if (s1 == null && s2 != null) {
            return 1;
        }
        return s1.compareToIgnoreCase(s2);
    }

    /**
     * Adds an element to a given collection, only if it is not null.
     *
     * @param el  the element
     * @param c   the collection to add the element to
     * @param <E> the type of the collection content
     */
    static <E> void addIfNotNull(E el, Collection<E> c) {
        if (el != null && c != null) {
            c.add(el);
        }
    }

    /**
     * Returns the size of the collection.
     *
     * @param collection the collection
     * @param <E>        the type of the collection
     * @return 0, or the size of the collection
     */
    static <E> int size(Collection<E> collection) {
        if (collection != null) {
            return collection.size();
        }
        return 0;
    }

    /**
     * Returns a shortened version of a given collection.
     *
     * @param c        the origin collection
     * @param maxItems the maximum items to get from the collection to return
     * @param <E>      the type
     * @return the shortened collection
     */
    static <E> Collection<E> shorten(Collection<E> c, int maxItems) {
        if (c == null) {
            return null;
        }
        if (c.size() <= maxItems) {
            return c;
        }
        int min = maxItems;
        Collection<E> rc = new ArrayList<>(min);
        Iterator<E> cIt = c.iterator();
        for (int i = 0; i < min; ++i) {
            E element = cIt.next();
            rc.add(element);
        }
        return rc;
    }

}
