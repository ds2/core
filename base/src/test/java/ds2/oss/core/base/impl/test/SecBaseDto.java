package ds2.oss.core.base.impl.test;

import java.nio.charset.Charset;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.SecurityBaseData;

/**
 * The dto.
 * 
 * @version 0.2
 * @author dstrauss
 */
@ApplicationScoped
public class SecBaseDto implements SecurityBaseData {
    @Override
    public byte[] getSalt() {
        return "mySaltedWord".getBytes(Charset.forName("utf-8"));
    }
    
    @Override
    public int getMinIteration() {
        return 20000;
    }
}
