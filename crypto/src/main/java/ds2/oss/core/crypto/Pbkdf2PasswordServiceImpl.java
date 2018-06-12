package ds2.oss.core.crypto;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.SymmetricKeyNames;
import ds2.oss.core.api.SymmetricKeyService;
import ds2.oss.core.api.crypto.BytesProvider;
import ds2.oss.core.api.crypto.CoreCryptoException;
import ds2.oss.core.api.crypto.PasswordService;
import ds2.oss.core.api.crypto.Pbkdf2;
import ds2.oss.core.statics.Converts;
import ds2.oss.core.statics.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.Base64;

@Dependent
@Pbkdf2
@Alternative
@Priority(100)
public class Pbkdf2PasswordServiceImpl implements PasswordService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private SymmetricKeyService symmetricKeyService;
    @Inject
    private SecurityBaseData securityBaseData;
    @Inject
    private BytesProvider bytesProvider;
    private char separator = ':';
    private Base64.Encoder encoder;
    private Base64.Decoder decoder;
    private int keyLength = 512;

    @PostConstruct
    public void onLoad() {
        encoder = Base64.getEncoder();
        decoder = Base64.getDecoder();
    }

    @Override
    public String encryptPw(char[] pw) throws CoreCryptoException {
        if (pw == null || pw.length <= 0) {
            return null;
        }
        byte[] salt = bytesProvider.createRandomByteArray(64);
        int iterations = Math.max(securityBaseData.getMinIteration(), 10125);
        byte[] hashResult = symmetricKeyService.performHashing(pw, salt, iterations, SymmetricKeyNames.PBKDF512, keyLength);
        StringBuilder sb = new StringBuilder(200);
        sb.append("pbkdf2");
        sb.append(separator).append(iterations);
        sb.append(separator).append(encoder.encodeToString(salt));
        sb.append(separator).append(encoder.encodeToString(hashResult));
        String result = sb.toString();
        LOG.debug("Returning hash result: {}", result);
        return result;
    }

    @Override
    public boolean isValidPassword(String hash, char[] pw) {
        boolean isValid = false;
        if (!Methods.isBlank(hash) && pw != null && hash.startsWith("pbkdf2")) {
            String[] splits = hash.split(String.valueOf(separator));
            if (splits.length >= 4) {
                int iterations = Converts.toInt(splits[1], 0);
                byte[] saltVal = decoder.decode(splits[2]);
                byte[] hashResult = symmetricKeyService.performHashing(pw, saltVal, iterations, SymmetricKeyNames.PBKDF512, keyLength);
                String hashedResult = encoder.encodeToString(hashResult);
                if (splits[3].equals(hashedResult)) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }
}
