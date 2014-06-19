/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.codec.boon;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.boon.json.ObjectMapper;
import org.boon.json.implementation.ObjectMapperImpl;

/**
 *
 * @author dstrauss
 */
@ApplicationScoped
public class BoonJsonCodec implements JsonCodec {

    private ObjectMapper om;

    @PostConstruct
    public void onLoad() {
        om = new ObjectMapperImpl();
    }

    @Override
    public String encode(Object z) throws CoreException {
        String rc = om.writeValueAsString(z);
        return rc;
    }

    @Override
    public Object decode(String a) throws CoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <E> E decode(String z, Class<E> c) throws CoreException {
        E rc = om.readValue(z, c);
        return rc;
    }

}
