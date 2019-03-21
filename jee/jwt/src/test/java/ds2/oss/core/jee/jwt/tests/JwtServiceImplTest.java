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
    public void h3_1_Example_JWT() throws JwtContentException {
        String token = to.createHeader(Algorithm.HMAC_SHA256, "JWT");
        assertEquals(token, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
    }

    @Test
    public void h6_1_Example_Unsecured_JWT() throws JwtContentException {
        String token = to.createHeader(Algorithm.NONE, null);
        assertEquals(token, "eyJhbGciOiJub25lIn0");
    }

    @Test
    public void h6_1_Example_Unsecured_JWT_Claims_Set() throws JwtContentException {
        TokenDataMap tokenData = new TokenDataMap();
        tokenData.put("iss", "joe");
        tokenData.put("exp", 1300819380);
        tokenData.put("http://example.com/is_root", true);
        String token = to.encodeBody(tokenData);
        assertEquals(token, "eyJpc3MiOiJqb2UiLCJleHAiOjEzMDA4MTkzODAsImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ");
    }
}
