/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.elasticsearch.test.support;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author dstrauss
 */
public class EsNodeSetter implements Callable<Void> {

  private ElasticSearchNode esNode;
  private Random random;

  public EsNodeSetter(ElasticSearchNode e) {
    esNode = e;
    random = new Random();
  }

  @Override
  @SuppressWarnings("SleepWhileInLoop")
  public Void call() throws Exception {
    while (!Thread.currentThread().isInterrupted()) {
      esNode.addTransport(new InetSocketAddress("localhost", random.nextInt(30000) + 1024));
      Thread.sleep(random.nextInt(2500) + 2000);
    }
    return null;
  }
}
