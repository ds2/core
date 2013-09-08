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
public class EnumModule<E extends Enum<E>> {
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
     * Inits the module.
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
}
