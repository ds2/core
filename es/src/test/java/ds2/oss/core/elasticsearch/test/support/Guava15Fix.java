/**
 * 
 */
package ds2.oss.core.elasticsearch.test.support;

import java.util.Set;

import javax.enterprise.inject.Produces;

import com.google.common.util.concurrent.Service;

/**
 * CDI fix for Guava 15.0.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class Guava15Fix {
    
    @Produces
    public Set<Service> dummyServices() {
        throw new AssertionError();
    }
}
