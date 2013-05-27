package ds2.oss.core.elasticsearch.test;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created with IntelliJ IDEA.
 * User: dstrauss
 * Date: 27.05.13
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractInjectionEnvironment {
    /**
     * The classpath scanner.
     */
    private static Weld weld = new Weld();
    /**
     * The container.
     */
    private static WeldContainer wc;
    @BeforeSuite
    public void onSuite() {
        wc = weld.initialize();
    }

    @AfterSuite
    public void onSuiteEnd() {
        weld.shutdown();
    }
    /**
     * Returns an instance of the given class.
     *
     * @param c
     *            the class
     * @return an instance
     */
    public static <T> T getInstance(final Class<T> c) {
        return wc.instance().select(c).get();
    }
}
