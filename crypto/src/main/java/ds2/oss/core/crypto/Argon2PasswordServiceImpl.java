package ds2.oss.core.crypto;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import ds2.oss.core.api.crypto.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.lang.invoke.MethodHandles;

@Dependent
public class Argon2PasswordServiceImpl implements PasswordService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Argon2 argon2;

    @PostConstruct
    public void onCreate() {
        argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i, 128, 128);
    }

    @Override
    public String encryptPw(char[] pw) {
        if (pw == null || pw.length <= 0) {
            return null;
        }
        LOG.debug("Will try to encrypt given pw");
        int iterations = 27;
        int memory = 65536;
        int parallel = 2;
        String hash = argon2.hash(iterations, memory, parallel, pw);
        LOG.debug("Hashed pw data is: {}", hash);
        return hash;
    }
}
