package ds2.oss.core.elasticsearch.test;

import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.api.annotations.EsCodec;
import ds2.oss.core.elasticsearch.impl.AbstractTypeCodec;

import java.util.Map;

/**
 * Created by dstrauss on 09.06.17.
 */
@EsCodec(String.class)
public class MyDummyStringCodec extends AbstractTypeCodec<String> implements TypeCodec<String> {
    @Override
    public String getIndexTypeName() {
        return "myStrings";
    }

    @Override
    public String toDto(Map<String, Object> fields) {
        return (String) fields.get("value");
    }


}
