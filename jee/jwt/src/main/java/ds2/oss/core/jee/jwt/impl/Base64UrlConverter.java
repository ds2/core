package ds2.oss.core.jee.jwt.impl;

import java.util.Base64;

public class Base64UrlConverter {
    private Base64.Encoder encoder;
    private Base64.Decoder decoder;
    private Base64.Encoder urlEncoder;

    public Base64UrlConverter() {
        encoder = Base64.getEncoder();
        urlEncoder = Base64.getUrlEncoder();
        decoder = Base64.getDecoder();
    }

    public String toBase64(byte[] bytes) {
        return encoder.encodeToString(bytes);
    }

    public String toBase64Url(byte[] bytes) {
        return urlEncoder.encodeToString(bytes).replaceAll("=", "");
    }

    public byte[] decodeFromBase64(String base64String) {
        return decoder.decode(base64String);
    }
}
