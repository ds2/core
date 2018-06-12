package ds2.oss.core.api.crypto;

import java.util.List;

/**
 * A service to get all currently known supported ECs by name.
 */
public interface EllipticCurveNames {
    List<String> getNames();
}
