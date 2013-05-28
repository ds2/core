package ds2.oss.core.elasticsearch.test.support;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dstrauss
 */
public class EsNodeGetter implements Callable<Void>{
  /**
   * A logger.
   */
  private static final Logger LOG= LoggerFactory.getLogger(EsNodeGetter.class);
  /**
   * The node.
   */
  private ElasticSearchNode esNode;
  private Random random;
  
  
  public EsNodeGetter(ElasticSearchNode e){
    esNode=e;
    random=new Random();
  }

  @Override
  @SuppressWarnings("SleepWhileInLoop")
  public Void call() throws Exception {
    while(!Thread.currentThread().isInterrupted()){
      Client cl=esNode.get();
      if(cl==null){
        LOG.error("Client is null!");
      }
      Thread.sleep(random.nextInt(100)+100);
    }
    return null;
  }
  
}
