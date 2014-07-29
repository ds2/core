/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.codec.jackson2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dstrauss
 */
@ApplicationScoped
public class JacksonJsonCodec implements JsonCodec {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ObjectMapper om;

    @PostConstruct
    public void onLoad() {
        LOG.debug("Starting up");
        om = new ObjectMapper();
    }

    @Override
    public String encode(Object z) throws CoreException {
        String rc = null;
        if (z == null) {
            throw new IllegalArgumentException("No object given to transform!");
        }
        try {
            rc = om.writeValueAsString(z);
        } catch (JsonProcessingException ex) {
            throw new CoreException(CoreErrors.JSON_ENCODING_FAILED, ex);
        }
        LOG.debug("Result is {}", rc);
        return rc;
    }

    @Override
    public Object decode(String a) throws CoreException {
        throw new UnsupportedOperationException("Please use the other decode method for better type handling!");
    }

    @Override
    public <E> E decode(String z, Class<E> c) throws CoreException {
        E rc = null;
        try {
            rc = om.readValue(z, c);
        } catch (IOException ex) {
            throw new CoreException(CoreErrors.JSON_DECODING_FAILED, ex);
        }
        return rc;
    }

}
