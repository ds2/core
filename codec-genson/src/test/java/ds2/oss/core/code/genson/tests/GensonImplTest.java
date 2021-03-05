package ds2.oss.core.code.genson.tests;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.dto.impl.PagedResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by dstrauss on 19.04.17.
 */
public class GensonImplTest extends AbstractInjectionEnvironment {
    private JsonCodec to;

    @BeforeClass
    public void onLoad() {
        to = getInstance(JsonCodec.class);
    }

    @Test
    public void testPagedResult1() throws CodecException {
        PagedResult<String> stringPagedResult = new PagedResult<>();
        stringPagedResult.setResultList(Arrays.asList("this", "is", "a", "test"));
        stringPagedResult.setMaxResults(4);
        String json = to.encode(stringPagedResult);
        Assert.assertNotNull(json);
        Assert.assertEquals(json, "{\"maxResults\":4,\"resultList\":[\"this\",\"is\",\"a\",\"test\"],\"startIndex\":0}");

        //now backwards
        PagedResult<String> rc = to.decode(json, PagedResult.class);
        Assert.assertNotNull(rc);
        Assert.assertEquals(rc, stringPagedResult);
    }

    @Test
    public void testPagedResult2() throws CodecException {
        PagedResult<Person> stringPagedResult = new PagedResult<>();
        stringPagedResult.setResultList(Arrays.asList(new Person("Dirk", 23, Gender.MALE), new Person("Mausi", 7, Gender.FEMALE)));
        stringPagedResult.setMaxResults(4);
        String json = to.encode(stringPagedResult);
        Assert.assertNotNull(json);
        Assert.assertEquals(json, "{\"maxResults\":4,\"resultList\":[{\"age\":23,\"gender\":\"MALE\",\"name\":\"Dirk\"},{\"age\":7,\"gender\":\"FEMALE\",\"name\":\"Mausi\"}],\"startIndex\":0}");

        //now backwards
        PagedResult<Person> instanceIdea = new PagedResult<>();
        PagedResult<Person> rc = to.decodeInto(json, instanceIdea);
        PagedResult<Person> rc3 = to.decode(json, PersonPagedResult.class);
        PagedResult<Person> rc2 = new Genson().deserialize(json, new GenericType<PagedResult<Person>>() {
        });
        Assert.assertNotNull(rc);
        Assert.assertEquals(rc2, stringPagedResult, "the GenericType approach does not work");
        Assert.assertEquals(rc3, stringPagedResult, "The super-classed approach does not work");
        //Assert.assertEquals(rc, stringPagedResult, "The decodeInto approach does not work!");
    }

    class MyClass {
        public Map<String, Set<String>> mapOfSets;
    }
}
