package ds2.oss.core.abstracts;

import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.EnumConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Abstract body for creating a bean to deal with enum conversions.
 *
 * @author dstrauss
 * @version 0.3
 * @param <Enu> the enum type
 * @param <Transp> the transport type
 */
public abstract class AbstractEnumKonverterImpl<Enu extends Enum<Enu>, Transp> implements EnumConverter<Enu, Transp> {
    private static final Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    protected boolean allowNullEnumValues=false;

    @Override
    public Enu decode(Transp a) throws CodecException {
        Enu rc=null;
        try {
            rc=toEnu(a);
        } catch(Exception e){
            LOG.debug("Error thrown when converting {} to an enum value!",a,e);
        }
        if(rc==null&&!allowNullEnumValues){
            throw new CodecException(CoreErrors.UnknownEnumValue);
        }
        LOG.debug("enum value for {} is considered {}", a, rc);
        return rc;
    }
    protected abstract Enu toEnu(Transp t);

    @Override
    public Transp encode(Enu z) throws CodecException {
        Transp rc=toTransport(z);
        LOG.debug("transport value for {} is considered {}", z, rc);
        return rc;
    }

    protected abstract Transp toTransport(Enu z);
}
