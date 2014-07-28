/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Marks a type to be a packet extension provider from Smack for XMPP.
 *
 * @author dstrauss
 * @version 0.3
 */
@Qualifier
@ApplicationScoped
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SmackPEProvider {

    /**
     * The element name.
     *
     * @return the element name
     */
    @Nonbinding
    String elementName() default "";

    /**
     * The namespace to watch for.
     *
     * @return the namespace
     */
    @Nonbinding
    String namespace() default "";
}
