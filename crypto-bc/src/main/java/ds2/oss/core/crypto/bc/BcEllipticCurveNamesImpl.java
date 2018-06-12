package ds2.oss.core.crypto.bc;

import ds2.oss.core.api.crypto.EllipticCurveNames;
import org.bouncycastle.jce.ECNamedCurveTable;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by deindesign on 04.04.16.
 */
@ApplicationScoped
public class BcEllipticCurveNamesImpl implements EllipticCurveNames{
    @Override
    public List<String> getNames() {
        List<String> rc=new ArrayList<>();
        Enumeration<String> enumNames=ECNamedCurveTable.getNames();
        while(enumNames.hasMoreElements()){
            rc.add(enumNames.nextElement());
        }
        Collections.sort(rc);
        return rc;
    }
}
