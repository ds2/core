/**
 * 
 */
package ds2.oss.core.base.impl.test;

import java.util.Set;

import javax.enterprise.inject.Produces;

import com.google.common.util.concurrent.Service;

/**
 * A CDI fix for Guava 15.0.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class Guava15Fix {
    
    @Produces
    Set<Service> dummyServices() {
        throw new AssertionError();
    }
}
