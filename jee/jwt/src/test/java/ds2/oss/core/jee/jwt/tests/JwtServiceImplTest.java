package ds2.oss.core.jee.jwt.tests;

import ds2.oss.core.base.impl.Base64Konverter;
import ds2.oss.core.codec.gson.GsonCodec;
import ds2.oss.core.jee.jwt.Algorithm;
import ds2.oss.core.jee.jwt.JwtContentException;
import ds2.oss.core.jee.jwt.JwtServiceImpl;
import ds2.oss.core.jee.jwt.TokenDataMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class JwtServiceImplTest {
    private JwtServiceImpl to;

    @BeforeClass
    public void onClass() {
        to = new JwtServiceImpl();
        to.setBase64Codec(new Base64Konverter());
        GsonCodec gsonCodec = new GsonCodec();
        gsonCodec.onLoad();
        to.setJsonCodec(gsonCodec);
    }

    @Test
    public void testNoAlg() throws JwtContentException {
        TokenDataMap tokenData = new TokenDataMap();
        String token = to.createToken(Algorithm.NONE, tokenData);
        assertEquals(token, "");
    }
}
