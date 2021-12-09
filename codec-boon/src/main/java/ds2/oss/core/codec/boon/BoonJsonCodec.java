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
package ds2.oss.core.codec.boon;

import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.JsonCodecException;
import org.boon.json.JsonParserFactory;
import org.boon.json.JsonSerializerFactory;
import org.boon.json.ObjectMapper;
import org.boon.json.implementation.ObjectMapperImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

/**
 * A json codec, using the boon json parser.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class BoonJsonCodec implements JsonCodec {
    private static final Logger LOG = Logger.getLogger(BoonJsonCodec.class.getName());

    private ObjectMapper om;

    @Override
    public Object decode(String a) throws CodecException {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public <E> E decode(String z, Class<E> c) throws JsonCodecException {
        return om.readValue(z, c);
    }

    @Override
    public <E> E decodeInto(String z, E instance) throws JsonCodecException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String encode(Object z) throws CodecException {
        LOG.entering(BoonJsonCodec.class.getName(), "encode", z);
        String jsonString = z == null ? null : om.writeValueAsString(z);
        LOG.exiting(BoonJsonCodec.class.getName(), "encode", jsonString);
        return jsonString;
    }

    @PostConstruct
    public void onLoad() {
        LOG.fine("Loading boon codec..");
        JsonParserFactory parser = new JsonParserFactory();
        JsonSerializerFactory serializer = new JsonSerializerFactory();
        serializer.useFieldsOnly();
        om = new ObjectMapperImpl(parser, serializer);
        LOG.fine("Done loading boon codec");
    }

}
