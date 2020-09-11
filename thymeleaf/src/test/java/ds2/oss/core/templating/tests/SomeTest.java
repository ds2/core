package ds2.oss.core.templating.tests;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.ContentTemplateService;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.templating.RequestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SomeTest extends AbstractInjectionEnvironment {
    private ContentTemplateService templateService;

    @BeforeClass
    public void onClass() {
        templateService = getInstance(ContentTemplateService.class);
    }

    @Test
    public void testPlainText() throws CoreException {
        RequestContext ctx = new RequestContext() {
            @Override
            public Locale getLocale() {
                return Locale.GERMANY;
            }
        };
        Map<String, Object> placeHolders = new HashMap<>();
        placeHolders.put("userName", "Timothy");
        String result = templateService.formatContent("templ1", ctx, placeHolders);
        assertEquals(result, "Hallo, Timothy");
    }
}
