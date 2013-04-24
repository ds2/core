/**
 * 
 */
package ds2.oss.core.interceptors.test;

import java.io.File;
import java.io.IOException;

import org.glassfish.ejb.config.EjbContainer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.internal.embedded.LifecycleException;
import org.glassfish.internal.embedded.Server;

/**
 * @author dstrauss
 * 
 */
public abstract class AbstractGlassfishController {
    private static Server server;
    private static GlassFish gf;
    
    /**
     * 
     */
    public AbstractGlassfishController() {
        // TODO Auto-generated constructor stub
    }
    
    public void onSuiteStart() throws LifecycleException, IOException,
        GlassFishException {
        
        File instRoot = new File("target/glassfish/instanceroot/config");
        instRoot.mkdirs();
        File instRoot2 = new File("target/glassfish/installroot");
        instRoot2.mkdirs();
        /*
         * Server.Builder builder = new Server.Builder("testgf1");
         * builder.logger(true); EmbeddedFileSystem.Builder efsb = new
         * EmbeddedFileSystem.Builder(); efsb.installRoot(new
         * File("target/glassfish/installroot")); efsb.instanceRoot(new
         * File("target/glassfish/instanceroot")); efsb.autoDelete(true);
         * EmbeddedFileSystem efs = efsb.build();
         * builder.embeddedFileSystem(efs); server = builder.build();
         * server.createPort(8080); HttpListener listener = new
         * HttpListener("http-listener-gfembed", 8080);
         * listener.setProtocol("http"); listener.setWebContainer(null);
         * ContainerBuilder<EmbeddedContainer> ejbContainer =
         * server.createConfig(ContainerBuilder.Type.ejb);
         * server.addContainer(ejbContainer); server.start();
         */
        
        gf = GlassFishRuntime.bootstrap().newGlassFish();
        gf.start();
        EjbContainer ejbC = gf.getService(EjbContainer.class);
        // ejbC.
    }
    
    public void onSuiteEnd() throws LifecycleException, GlassFishException {
        gf.stop();
    }
    
}
