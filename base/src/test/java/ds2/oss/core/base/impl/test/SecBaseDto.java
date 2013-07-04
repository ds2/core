package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.SecurityBaseData;

import javax.enterprise.context.ApplicationScoped;
import java.nio.charset.Charset;

/**
 * The dto.
 */
@ApplicationScoped
public class SecBaseDto implements SecurityBaseData{
    @Override
    public byte[] getSalt() {
        return "mySaltedWord".getBytes(Charset.forName("utf-8"));
    }

    @Override
    public int getMinIteration() {
        return 20000;
    }
}
