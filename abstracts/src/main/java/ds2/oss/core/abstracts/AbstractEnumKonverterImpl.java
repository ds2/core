/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
 * @param <Enu>    the enum type
 * @param <Transp> the transport type
 * @author dstrauss
 * @version 0.3
 */
public abstract class AbstractEnumKonverterImpl<Enu extends Enum<Enu>, Transp> implements EnumConverter<Enu, Transp> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    protected boolean allowNullEnumValues = false;

    @Override
    public Enu decode(Transp a) throws CodecException {
        Enu rc = null;
        try {
            rc = toEnu(a);
        } catch (Exception e) {
            LOG.debug("Error thrown when converting {} to an enum value!", a, e);
        }
        if (rc == null && !allowNullEnumValues) {
            throw new CodecException(CoreErrors.UnknownEnumValue);
        }
        LOG.debug("enum value for {} is considered {}", a, rc);
        return rc;
    }

    protected abstract Enu toEnu(Transp t);

    @Override
    public Transp encode(Enu z) throws CodecException {
        Transp rc = toTransport(z);
        LOG.debug("transport value for {} is considered {}", z, rc);
        return rc;
    }

    protected abstract Transp toTransport(Enu z);
}
