/*
 * Copyright 2012-2013 Dirk Strauss
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
/**
 * 
 */
package ds2.oss.core.base.impl;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * The idea for an enum module.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the enum to wrap
 */
@Embeddable
public abstract class EnumModule<E extends Enum<E>> {
    /**
     * The represented int value.
     */
    @Column(name = "value")
    private int value;
    /**
     * The converter to use.
     */
    @Transient
    private NumericalEnumConverter<E> conv;
    /**
     * The method name to use for enum value lookup.
     */
    private String reflMethodName = "getById";
    
    /**
     * Inits the module. Be aware that this module cannot provide a default constructor.
     * 
     * @param c
     *            the enum class
     * 
     */
    public EnumModule(final Class<E> c) {
        conv = new NumericalEnumConverter<>(c);
    }
    
    /**
     * Inits the module.
     * 
     * @param c
     *            the enum class
     * @param reflMethod
     *            the name of the method within the enum class to perform a lookup via an int value.
     * 
     */
    public EnumModule(final Class<E> c, final String reflMethod) {
        this(c);
        reflMethodName = reflMethod;
    }
    
    /**
     * Sets the value.
     * 
     * @param e
     *            the enum value to use.
     */
    public void setValue(final E e) {
        value = conv.getValue(e);
    }
    
    /**
     * Returns the current enum value.
     * 
     * @return the enum value
     */
    public E getValue() {
        return conv.getEnumByReflection(value, reflMethodName);
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EnumModule (value=");
        builder.append(value);
        builder.append(", reflMethodName=");
        builder.append(reflMethodName);
        builder.append(")");
        return builder.toString();
    }
    
}
