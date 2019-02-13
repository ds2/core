/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.code.genson;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.JsonCodecException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.text.SimpleDateFormat;

/**
 * Created by dstrauss on 19.04.17.
 */
@Dependent
public class GensonCodec implements JsonCodec {
    private Genson genson;

    @PostConstruct
    public void onLoad() {
        genson = new GensonBuilder()
                .useDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .useIndentation(false)
                .setSkipNull(true)
                .useRuntimeType(true)
                .useConstructorWithArguments(true)
                .create();
    }

    @Override
    public Object decode(String a) throws CodecException {
        throw new UnsupportedOperationException("Not supported. Please use the other decode method!");
    }

    @Override
    public String encode(Object z) throws CodecException {
        return genson.serialize(z);
    }

    @Override
    public <E> E decode(String z, Class<E> c) throws JsonCodecException {
        return genson.deserialize(z, GenericType.of(c));
    }

    @Override
    public <E> E decodeInto(String z, E instance) throws JsonCodecException {
        E rc = genson.deserializeInto(z, instance);
        return rc;
    }
}
