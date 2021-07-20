package ds2.oss.core.owbtests;

import ds2.oss.core.api.Base64Codec;
import org.apache.openwebbeans.junit5.Cdi;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by dstrauss on 19.04.17.
 */
@Cdi(reusable = true)
public class Base64CodecTest {
    @Inject
    private Base64Codec to;

    @Test
    public void testExists() {
        assertNotNull(to);
    }
}
